package cn.com.yuzhushui.schedule.component;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.com.yuzhushui.schedule.component.job.AbstractServerJob;
import cn.com.yuzhushui.schedule.component.job.OnceServerJob;
import cn.com.yuzhushui.schedule.component.job.RepeatServerJob;
import cn.com.yuzhushui.schedule.job.biz.entity.JobInfo;
import cn.com.yuzhushui.schedule.job.biz.entity.JobSnapshot;
import cn.com.yuzhushui.schedule.job.enums.JobInfoEnum;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月29日下午1:29:58
 **/
@Component
public class SchedulePackage {
	
private static final Logger LOG = LoggerFactory.getLogger(SchedulePackage.class);
	
	@Resource
	private Scheduler scheduler;
	
	/**
	 * 判断scheduler中是否包含特定的JobDetail
	 * @param jobInfo
	 * @return
	 * @throws SchedulerException
	 */
	public boolean containsJobDetail(JobInfo jobInfo) throws SchedulerException {
		JobKey jobKey = getJobKeyByJobInfo(jobInfo);
		JobDetail jobDetail = scheduler.getJobDetail(jobKey);
		if (jobDetail == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 根据jobInfo的name、group获取JobKey
	 * @param jobInfo
	 * @return
	 */
	public JobKey getJobKeyByJobInfo(JobInfo jobInfo) {
		String name = jobInfo.getName();
		String group = jobInfo.getGroups();
		JobKey jobKey = JobKey.jobKey(name, group);
		return jobKey;
	}

	/**
	 * 根据jobInfo的name、group获取TriggerKey
	 * @param jobInfo
	 * @return
	 */
	public TriggerKey getTriggerKeyByJobInfo(JobInfo jobInfo) {
		String name = jobInfo.getName();
		String group = jobInfo.getGroups();

		TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
		return triggerKey;
	}
	
	/**
	 * 根据jobSnapshot的name、group获取JobKey
	 * @param jobSnapshot
	 * @return
	 */
	public JobKey getJobKeyByJobSnapshot(JobSnapshot jobSnapshot) {
		String name = jobSnapshot.getName() + "_reload_" + jobSnapshot.getId();
		String group = jobSnapshot.getGroups() + "_reload_" + jobSnapshot.getId();

		JobKey jobKey = JobKey.jobKey(name, group);
		return jobKey;
	}
	
	/**
	 * 根据jobSnapshot的name、group获取TriggerKey
	 * @param jobSnapshot
	 * @return
	 */
	public TriggerKey getTriggerKeyByJobSnapshot(JobSnapshot jobSnapshot) {
		String name = jobSnapshot.getName() + "_reload_" + jobSnapshot.getId();
		String group = jobSnapshot.getGroups() + "_reload_" + jobSnapshot.getId();
		
		TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
		return triggerKey;
	}

	/**
	 * 根据jobInfo创建一个JobDetail
	 * @param jobInfo
	 * @return
	 */
	public JobDetail createJobDetailByJobInfo(JobInfo jobInfo) {
		JobInfoEnum.TYPE type = jobInfo.getType();
		Class<? extends Job> jobClass = null;
		if (type.equals(JobInfoEnum.TYPE.ONCE)) {
			jobClass = OnceServerJob.class;
		} else if (type.equals(JobInfoEnum.TYPE.REPEAT)) {
			jobClass = RepeatServerJob.class;
		} else {
			LOG.warn("Case unexpected job info type: " + type + ", jobInfo: " + jobInfo);
			return null;
		}
		JobKey jobKey = getJobKeyByJobInfo(jobInfo);
		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).usingJobData(AbstractServerJob.JOB_INFO_ID, jobInfo.getId()).build();
		return jobDetail;
	}
	
	/**
	 * 根据jobSnapshot创建一个JobDetail
	 * @param jobSnapshot
	 * @return
	 */
	public JobDetail createJobDetailByJobSnapshot(JobSnapshot jobSnapshot, JobInfo jobInfo) {
		JobInfoEnum.TYPE type = jobInfo.getType();
		Class<? extends Job> jobClass = null;
		if (type.equals(JobInfoEnum.TYPE.ONCE)) {
			jobClass = OnceServerJob.class;
		} else if (type.equals(JobInfoEnum.TYPE.REPEAT)) {
			jobClass = RepeatServerJob.class;
		} else {
			LOG.warn("Case unexpected job info type: " + type + ", jobSnapshot: " + jobSnapshot);
			return null;
		}
		JobKey jobKey = getJobKeyByJobSnapshot(jobSnapshot);
		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).usingJobData(AbstractServerJob.RELOAD_KEY, true)
				.usingJobData(AbstractServerJob.JOB_SNAPSHOT_ID, jobSnapshot.getId())
				.usingJobData(AbstractServerJob.JOB_INFO_ID, jobSnapshot.getJobInfoId()).build();
		return jobDetail;
	}
	
	/**
	 * 创建一个CronTrigger
	 * @param jobInfo
	 * @return
	 * @throws SchedulerException
	 */
	public Trigger createCronTrigger(JobInfo jobInfo) throws SchedulerException {
		String cron = null;
		JobInfoEnum.TYPE type = jobInfo.getType();
		if (type.equals(JobInfoEnum.TYPE.ONCE)) {
			cron = datetimeToCron(jobInfo.getTime());
		} else if (type.equals(JobInfoEnum.TYPE.REPEAT)) {
			cron = jobInfo.getCron();
		} else {
			LOG.warn("Case unexpected job info type: " + type + ", jobInfo: " + jobInfo);
			return null;
		}
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
		TriggerKey triggerKey = getTriggerKeyByJobInfo(jobInfo);
		CronTrigger trigger = TriggerBuilder.newTrigger()
								.withIdentity(triggerKey)
								.withSchedule(cronScheduleBuilder).build();
		return trigger;
	}
	
	/**
	 * 创建一个SimpleTrigger，并且立即执行
	 * @param jobSnapshot
	 * @return
	 * @throws SchedulerException
	 */
	public Trigger createSimpleTrigger(JobSnapshot jobSnapshot) throws SchedulerException {
		TriggerKey triggerKey = getTriggerKeyByJobSnapshot(jobSnapshot);
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(SimpleScheduleBuilder.simpleSchedule()).startNow().build();
		return trigger;
	}
	
	/**
	 * 将JobDetail、Trigger添加到scheduler中
	 * @param jobDetail
	 * @param trigger
	 * @return
	 * @throws SchedulerException
	 */
	public Date scheduleJob(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
		return scheduler.scheduleJob(jobDetail, trigger);
	}

	/**
	 *  根据JobKey删除指定的Job
	 * @param jobKey
	 * @return
	 * @throws SchedulerException
	 */
	public boolean deleteJob(JobKey jobKey) throws SchedulerException {
		return scheduler.deleteJob(jobKey);
	}
	
	/**
	 *  根据指定的TriggerKey，删除旧的Trigger，添加新的Trigger
	 * @param triggerKey
	 * @param newTrigger
	 * @return
	 * @throws SchedulerException
	 */
	public Date rescheduleJob(TriggerKey triggerKey, Trigger newTrigger) throws SchedulerException {
		return scheduler.rescheduleJob(triggerKey, newTrigger);
	}

	/**
	 * 将Date时间转换为 cron 表达式.
	 * @param date
	 * @return
	 */
	public String datetimeToCron(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		StringBuilder cronBuilder = new StringBuilder();
		cronBuilder.append(c.get(Calendar.SECOND));
		cronBuilder.append(" ");
		cronBuilder.append(c.get(Calendar.MINUTE));
		cronBuilder.append(" ");
		cronBuilder.append(c.get(Calendar.HOUR_OF_DAY));
		cronBuilder.append(" ");
		cronBuilder.append(c.get(Calendar.DAY_OF_MONTH));
		cronBuilder.append(" ");
		// JANUARY 是 0.
		cronBuilder.append(c.get(Calendar.MONTH) + 1);
		cronBuilder.append(" ");
		cronBuilder.append("?");
		cronBuilder.append(" ");
		cronBuilder.append(c.get(Calendar.YEAR));
		String cron = cronBuilder.toString();
		return cron;
	}
	public Scheduler getScheduler() {
		return scheduler;
	};

}
