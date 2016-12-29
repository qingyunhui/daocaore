package cn.com.yuzhushui.schedule.component.job;

import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.conn.ConnectTimeoutException;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qing.yun.hui.common.utils.HttpClientUtils;
import cn.com.yuzhushui.schedule.component.ServerJobHelper;
import cn.com.yuzhushui.schedule.component.policy.InvokePolicy;
import cn.com.yuzhushui.schedule.component.policy.InvokePolicyFactory;
import cn.com.yuzhushui.schedule.enums.JobInfoEnum;
import cn.com.yuzhushui.schedule.enums.JobSnapshotEnum;
import cn.com.yuzhushui.schedule.enums.JobStatus;
import cn.com.yuzhushui.schedule.enums.MethodFlag;
import cn.com.yuzhushui.schedule.job.bean.JobExecutingResponse;
import cn.com.yuzhushui.schedule.job.bean.JobInvokeResponse;
import cn.com.yuzhushui.schedule.job.bean.JobRequest;
import cn.com.yuzhushui.schedule.job.bean.JobResult;
import cn.com.yuzhushui.schedule.job.biz.entity.JobInfo;
import cn.com.yuzhushui.schedule.job.biz.entity.JobSnapshot;
import cn.com.yuzhushui.schedule.job.biz.service.JobInfoService;
import cn.com.yuzhushui.schedule.job.biz.service.JobSnapshotService;

import com.alibaba.fastjson.JSON;

public abstract class AbstractServerJob implements Job {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 连接超时或响应超时. 总体超时时间为: TIME_OUT + TIME_OUT.
	protected static final int TIME_OUT = 1000 * 5;
	
	public static final String JOB_SNAPSHOT_ID = "jobSnapshotId";
	
	public static final String JOB_INFO_ID = "jobInfoId";
	
	public static final String RELOAD_KEY = "isReload";
	
	protected JobSnapshot jobSnapshot;
	
	protected JobInfo jobInfo;
	
	protected final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// 初始化成员变量.
		init(context);
		/**************** init ****************/
		if (jobSnapshot.getStatus().equals(JobSnapshotEnum.STATUS.INIT)) {
			// 准备进入 invoke 阶段.
			initToInvoke();
		}
		/**************** invoke ****************/
		if (jobSnapshot.getStatus().equals(JobSnapshotEnum.STATUS.INVOKING)) {
			String url = null;
			// 获取 InvokePolicy
			InvokePolicy jobExecutePolicy = getInvokePolicy();
			while (true) {
				try {
					url = jobExecutePolicy.getNextUrl();
					if (url == null) {
						// 所有的链接失败.
						jobFail("Invoking 阶段, 调用目标服务器失败, 任务结束.");
						return;
					}
					
					// 调用目标服务器,启动任务.
					JobInvokeResponse invokeRes = invoke(url);
					if (invokeRes.isInvokedSucc()) {
						// 任务调用成功
						invokeToExecute();
						break;
					} else {
						// 调用失败.
						throw new RuntimeException(invokeRes.getErrorMsg());
					}
				}
				catch (Exception e) {
					invokeFail(e.getMessage());
					if (logger.isErrorEnabled()) {
						logger.error("Invoke " + url + " error! " + jobSnapshot.toString(), e);
					}
				}
			}
		}
		
		/**************** execute ****************/
		if (jobSnapshot.getStatus().equals(JobSnapshotEnum.STATUS.EXECUTING)) {
			
			// 正常等待时间,单位秒
			long sleepSecond = 10;
			// 异常情况下等待时间,单位秒
			long exceptionSleepSecond = 0L;
			// 获取任务结果失败次数.
			int failCount = 0;
			// 获取任务结果次数
			int getResultCount = 0;
			
			while (true) {
				try {
					getResultCount++;
					// 失败 5 次,则不再继续下去..
					if (failCount >= 5) {
						jobFail("Executing ,连接目标服务器 " + failCount + " 次失败, 任务结束.");
						return;
					}
					// 获取任务执行情况或者结果.
					JobExecutingResponse exeRes = getExecuteResult();
					JobStatus jobStatus = exeRes.getJobStatus();
					if (jobStatus.equals(JobStatus.EXECUTING)) {
						// 处理正在执行的情况.
						handleJobExecuting();
						// 等待一会儿, 然后再去获取结果.
						TimeUnit.SECONDS.sleep(sleepSecond);
						if (getResultCount == 10) {
							// 10次没有取得结果, 那么等待时间改为 1 分钟
							sleepSecond = 60;
						} else if (getResultCount == 20) {
							// 20次没有取得结果, 那么等待时间为 5 分钟.
							sleepSecond = 60 * 5;
						}
						continue;
					} else if (jobStatus.equals(JobStatus.FINISHED)) {
						JobResult jobResult = exeRes.getJobResult();
						// 任务完成
						handleJobCompleted(jobResult);
						return;
					} else if (jobStatus.equals(JobStatus.UNKNOW)) {
						// 目标服务器没有找到任务.
						handleJobUnknow();
						return;
					}
				}
				catch (Exception e) {
					// 获取任务结果失败. 等待多一分钟.
					exceptionSleepSecond += 60L;
					// 失败次数 + 1;
					failCount++;
					// 记录执行情况.
					getExecuteResFail(e.getMessage());
					try {
						TimeUnit.SECONDS.sleep(exceptionSleepSecond);
					}
					catch (InterruptedException e1) {
						logger.error("", e1);
					}
				}
			}
		}
	}
	
	/**
	 * 在目标服务器,获取不到任务信息时.
	 */
	protected void handleJobUnknow() {
		String detail = "目标服务器有没有这条任务执行的记录或结果. " + getNowTime() + "\n";
		jobSnapshot.setStatus(JobSnapshotEnum.STATUS.ERROR);
		jobSnapshot.setResult("目标服务器有没有这条任务执行的记录或结果.");
		jobSnapshot.setDetail(jobSnapshot.getDetail() + detail);
		jobSnapshot.setTimeConsume(0L);
		ServerJobHelper.getComponent(JobSnapshotService.class).update(jobSnapshot);
	}
	
	/**
	 * 处理任务完成的情况.
	 */
	protected void handleJobCompleted(JobResult jobResult) {
		if (jobResult.isSuccess()) {
			String detail = "任务已结束,并且执行成功. 结果[" + jobResult.getResult() + "] " + getNowTime() + "\n";
			jobSnapshot.setStatus(JobSnapshotEnum.STATUS.COMPLETED);
			jobSnapshot.setResult(jobResult.getResult());
			jobSnapshot.setTimeConsume(jobResult.getTimeConsume());
			jobSnapshot.setDetail(jobSnapshot.getDetail() + detail);
			ServerJobHelper.getComponent(JobSnapshotService.class).update(jobSnapshot);
		} else {
			String detail = "任务已结束,但执行时发生异常. 异常信息[" + jobResult.getResult() + "] " + getNowTime() + "\n";
			jobSnapshot.setStatus(JobSnapshotEnum.STATUS.ERROR);
			jobSnapshot.setResult("任务已结束,但执行时发生异常. 异常信息[" + jobResult.getResult() + "] ");
			jobSnapshot.setDetail(jobSnapshot.getDetail() + detail);
			jobSnapshot.setTimeConsume(jobResult.getTimeConsume());
			ServerJobHelper.getComponent(JobSnapshotService.class).update(jobSnapshot);
		}
	}
	
	/**
	 * 处理任务正在执行的情况.
	 */
	protected void handleJobExecuting() {
		String detail = "执行中... " + getNowTime() + "\n";
		jobSnapshot.setDetail(jobSnapshot.getDetail() + detail);
		ServerJobHelper.getComponent(JobSnapshotService.class).update(jobSnapshot);
	}
	
	/**
	 * 获取任务执行结果失败.
	 */
	protected void getExecuteResFail(String errorMessage) {
		String detail = "获取任务执行情况失败, 错误信息:[" + errorMessage + "] " + getNowTime() + "\n";
		jobSnapshot.setDetail(jobSnapshot.getDetail() + detail);
		ServerJobHelper.getComponent(JobSnapshotService.class).update(jobSnapshot);
	}
	
	/**
	 * 获取任务执行情况或者结果.
	 */
	protected JobExecutingResponse getExecuteResult() throws ConnectTimeoutException, SocketTimeoutException, Exception {
		String detail = "获取任务执行情况. " + getNowTime() + "\n";
		jobSnapshot.setDetail(jobSnapshot.getDetail() + detail);
		ServerJobHelper.getComponent(JobSnapshotService.class).update(jobSnapshot);
		JobRequest req = new JobRequest();
		req.setJobDetailId(jobSnapshot.getId());
		req.setMethodFlag(MethodFlag.EXECUTING);
		req.setClassFullPath(jobInfo.getClassPath());
		String reqBody = JSON.toJSONString(req);
		String resBody = HttpClientUtils.post(jobSnapshot.getUrl(), reqBody, "application/json", "utf-8", TIME_OUT,TIME_OUT);
		JobExecutingResponse exeRes = JSON.parseObject(resBody, JobExecutingResponse.class);
		return exeRes;
	}
	
	/**
	 * 获取调用策略.
	 */
	protected InvokePolicy getInvokePolicy() {
		JobInfoEnum.INVOKE_POLICY policy = jobInfo.getInvokePolicy();
		String urls = jobInfo.getUrls();
		List<String> urlList = Arrays.asList(urls.split(","));
		return ServerJobHelper.getComponent(InvokePolicyFactory.class).getJobExecutePolicy(policy, urlList);
	}
	
	/**
	 * 任务失败时.
	 */
	protected void jobFail(String errorMessage) {
		String detail = errorMessage + getNowTime() + "\n";
		jobSnapshot.setResult(errorMessage);
		jobSnapshot.setDetail(jobSnapshot.getDetail() + detail);
		jobSnapshot.setTimeConsume(0L);
		jobSnapshot.setStatus(JobSnapshotEnum.STATUS.ERROR);
		ServerJobHelper.getComponent(JobSnapshotService.class).update(jobSnapshot);
	}
	
	/**
	 * 调用失败.
	 */
	protected void invokeFail(String errorMessage) {
		String detail = "调用失败. 错误信息[" + errorMessage + "] " + getNowTime() + "\n";
		jobSnapshot.setDetail(jobSnapshot.getDetail() + detail);
		ServerJobHelper.getComponent(JobSnapshotService.class).update(jobSnapshot);
	}
	
	/**
	 * 调用成功.准备开始 Executing 阶段.
	 */
	protected void invokeToExecute() {
		String detail = "调用成功, 开始执行任务. " + getNowTime() + "\n";
		jobSnapshot.setStatus(JobSnapshotEnum.STATUS.EXECUTING);
		jobSnapshot.setDetail(jobSnapshot.getDetail() + detail);
		ServerJobHelper.getComponent(JobSnapshotService.class).update(jobSnapshot);
	}
	
	/**
	 * 调用目标服务器.
	 */
	protected JobInvokeResponse invoke(String url) throws Exception {
		String ip = getIpFromUrl(url);
		String detail = "调用 " + url + " " + getNowTime() + "\n";
		jobSnapshot.setUrl(url);
		jobSnapshot.setIp(ip);
		jobSnapshot.setDetail(jobSnapshot.getDetail() + detail);
		ServerJobHelper.getComponent(JobSnapshotService.class).update(jobSnapshot);
		JobRequest req = new JobRequest();
		req.setJobDetailId(jobSnapshot.getId());
		req.setMethodFlag(MethodFlag.INVOKE);
		req.setClassFullPath(jobInfo.getClassPath());
		req.setParam(jobInfo.getParam());
		String reqBody = JSON.toJSONString(req);
		String resBody = HttpClientUtils.post(url, reqBody, "application/json", "utf-8", TIME_OUT, TIME_OUT);
		JobInvokeResponse invokeRes = JSON.parseObject(resBody, JobInvokeResponse.class);
		return invokeRes;
	}
	
	/**
	 * 初始化成功后, 记录信息, 准备开始执行 invoke 阶段.
	 */
	protected void initToInvoke() {
		String detail = "准备调用目标服务器. " + getNowTime() + "\n";
		jobSnapshot.setStatus(JobSnapshotEnum.STATUS.INVOKING);
		jobSnapshot.setDetail(jobSnapshot.getDetail() + detail);
		ServerJobHelper.getComponent(JobSnapshotService.class).update(jobSnapshot);
	}
	
	/**
	 * 初始化所需的对象.
	 */
	protected void init(JobExecutionContext context) {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		
		Integer jobInfoId = jobDataMap.getInt(JOB_INFO_ID);
		this.jobInfo = ServerJobHelper.getComponent(JobInfoService.class).query(jobInfoId);
		
		// 获取 jobInfo 和 jobSnapshot
		boolean isReload = jobDataMap.getBoolean(RELOAD_KEY);
		if (isReload) {
			Integer jobSnapshotId = jobDataMap.getInt(JOB_SNAPSHOT_ID);
			jobSnapshot = ServerJobHelper.getComponent(JobSnapshotService.class).query(jobSnapshotId);
		} else {
			jobSnapshot = new JobSnapshot();
			jobSnapshot.setJobInfoId(jobInfoId);
			jobSnapshot.setName(jobInfo.getName());
			jobSnapshot.setGroup(jobInfo.getGroup());
			jobSnapshot.setStatus(JobSnapshotEnum.STATUS.INIT);
			jobSnapshot.setDetail("初始化 " + getNowTime() + "\n");
			ServerJobHelper.getComponent(JobSnapshotService.class).add(jobSnapshot);
		}
	}
	
	/**
	 * 获取现在的时间 yyyy-MM-dd HH:mm:ss
	 */
	private String getNowTime() {
		return sdf.format(new Date());
	}
	
	/**
	 * 从URL里提取IP或域名.
	 */
	private String getIpFromUrl(String url) {
		url = url.substring(url.indexOf("//") + 2);
		url = url.substring(0, url.indexOf("/"));
		if (url.contains(":"))
			return url.substring(0, url.indexOf(":"));
		return url;
	}
}
