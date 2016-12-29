package cn.com.yuzhushui.schedule.component.job;

import cn.com.yuzhushui.schedule.component.ServerJobHelper;
import cn.com.yuzhushui.schedule.job.bean.JobResult;
import cn.com.yuzhushui.schedule.job.biz.service.JobInfoService;

/**
 * 一次性任务,在执行后会删除 JobInfo.
 * 
 */
public class OnceServerJob extends AbstractServerJob {
	
	@Override
	protected void jobFail(String errorMessage) {
		super.jobFail(errorMessage);
		int result = ServerJobHelper.getComponent(JobInfoService.class).delete(jobInfo.getId());
		if (result!=1) {
			logger.error("jobInfoService.deleteJobInfoById fail! ");
		}
	}
	
	@Override
	protected void handleJobCompleted(JobResult jobResult) {
		super.handleJobCompleted(jobResult);
		int result = ServerJobHelper.getComponent(JobInfoService.class).delete(jobInfo.getId());
		if (result!=1) {
			logger.error("jobInfoService.deleteJobInfoById fail! ");
		}
	}
	
	@Override
	protected void handleJobUnknow() {
		super.handleJobUnknow();
		int result = ServerJobHelper.getComponent(JobInfoService.class).delete(jobInfo.getId());
		if (result!=1) {
			logger.error("jobInfoService.deleteJobInfoById fail! ");
		}
	}
}
