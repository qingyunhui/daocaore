package cn.com.yuzhushui.schedule.job.bean;

import java.io.Serializable;

import cn.com.yuzhushui.schedule.enums.JobStatus;

/**
 * 封装了请求执行结果等相关参数的DO
 * <p>
 * 注：这个response是在server端调用executing()方法的时候返回的结果
 * </p>
 * @author ding.xin
 * 
 */
public class JobExecutingResponse implements Serializable {

	private static final long serialVersionUID = 5898220952330641136L;

	/**
	 * 标识job执行状态
	 */
	private JobStatus jobStatus;

	/**
	 * 执行完成后返回的结果
	 */
	private JobResult jobResult;

	public JobStatus getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}

	public JobResult getJobResult() {
		return jobResult;
	}

	public void setJobResult(JobResult jobResult) {
		this.jobResult = jobResult;
	}

}
