package cn.com.yuzhushui.schedule.component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import qing.yun.hui.common.utils.DateUtil;
import cn.com.yuzhushui.schedule.job.biz.entity.JobInfo;
import cn.com.yuzhushui.schedule.job.biz.entity.JobSnapshot;
import cn.com.yuzhushui.schedule.job.biz.service.JobInfoService;
import cn.com.yuzhushui.schedule.job.biz.service.JobSnapshotService;
import cn.com.yuzhushui.schedule.job.enums.JobInfoEnum;
import cn.com.yuzhushui.schedule.job.enums.JobSnapshotEnum;

/***
 ** @category 从数据库中查询所有待执行的job
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月29日上午11:32:35
 **/
@Component
public class Launch {
	
	private static final Logger logger = LoggerFactory.getLogger(Launch.class);

	@Autowired
	private JobInfoService jobInfoService;

	@Autowired
	private JobSnapshotService jobSnapshotService;

	@Autowired
	private SchedulePackage schedulePackage;

	@PostConstruct
	public void init() {

		logger.info("Tms server launcher starting...");

		/***************** Register activity job *****************/
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("isActivity", JobInfoEnum.IS_ACTIVITY.ENABLED.getCode());
		List<JobInfo> activityJobInfoList = jobInfoService.query(map);
		for (JobInfo jobInfo : activityJobInfoList) {
			try {
				JobDetail jobDetail = schedulePackage.createJobDetailByJobInfo(jobInfo);
				Trigger trigger = schedulePackage.createCronTrigger(jobInfo);
				schedulePackage.scheduleJob(jobDetail, trigger);
				logger.info("Schedule job success, job info id: " + jobInfo.getId());
				if (logger.isDebugEnabled()) {
					logger.debug("Job info: " + jobInfo);
				}
			} catch (Exception e) {
				logger.error("Schedule job fail!", e);
			}
		}

		/***************** Reload job snapshot *****************/
		map.clear();
		map.put("statusList", Arrays.asList(JobSnapshotEnum.STATUS.INIT.toString(),JobSnapshotEnum.STATUS.INVOKING.toString(),JobSnapshotEnum.STATUS.EXECUTING.toString()));
		List<JobSnapshot> jobSnapshotList = jobSnapshotService.query(map);
		for (JobSnapshot jobSnapshot : jobSnapshotList) {
			try {
				Integer jobInfoId = jobSnapshot.getJobInfoId();
				JobInfo jobInfo = jobInfoService.query(jobInfoId);
				if (jobInfo == null) {
					// reload job 时, 对应的 job info 被删除了, 则把这个 job 状态改为 error.
					String result = "Tms 启动时, 重新加载未完成的任务, 但这个任务信息已经被删除.";
					String detail = result + DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss") + "\n";
					jobSnapshot.setStatus(JobSnapshotEnum.STATUS.ERROR);
					jobSnapshot.setResult(result);
					jobSnapshot.setDetail(detail);
					jobSnapshot.setTimeConsume(0L);
					jobSnapshotService.update(jobSnapshot,false);
					break;
				}
				if (JobInfoEnum.IS_ACTIVITY.ENABLED.getValue()!=jobInfo.getIsActivity().intValue()) {
					// reload job 时, 对应的 job info 已经为 not activity 了, 则把这个 job
					// 状态改为 error.
					String result = "Tms 启动时, 重新加载未完成的任务, 但这个任务信息已经被置为不激活.";
					String detail = result + DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss") + "\n";
					jobSnapshot.setStatus(JobSnapshotEnum.STATUS.ERROR);
					jobSnapshot.setResult(result);
					jobSnapshot.setDetail(detail);
					jobSnapshot.setTimeConsume(0L);
					jobSnapshotService.update(jobSnapshot,false);
					break;
				}
				JobDetail jobDetail = schedulePackage.createJobDetailByJobSnapshot(jobSnapshot, jobInfo);
				// 重新注册尚未完成的任务, 并且立即执行, 且为一次性任务.
				Trigger trigger = schedulePackage.createSimpleTrigger(jobSnapshot);
				schedulePackage.scheduleJob(jobDetail, trigger);
				logger.info("Reload job snapshot success, job snapshot id: " + jobSnapshot.getId());
				if (logger.isDebugEnabled()) {
					logger.debug("job snapshot: " + jobSnapshot);
				}
			} catch (Exception e) {
				String result = "Reload job snapshot fail.";
				String detail = "Reload job snapshot fail, set status error." + DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss") + "\n";
				jobSnapshot.setStatus(JobSnapshotEnum.STATUS.ERROR);
				jobSnapshot.setResult(result);
				jobSnapshot.setDetail(detail);
				jobSnapshot.setTimeConsume(0L);
				jobSnapshotService.update(jobSnapshot,false);
				logger.error("Reload job snapshot fail.", e);
			}
		}
		logger.info("Tms server launcher started!");
	}
}
