package cn.com.yuzhushui.schedule.job.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qing.yun.hui.common.utils.BeanUtil;
import cn.com.yuzhushui.schedule.common.base.BaseServiceImpl;
import cn.com.yuzhushui.schedule.component.SchedulePackage;
import cn.com.yuzhushui.schedule.job.biz.dao.JobInfoDao;
import cn.com.yuzhushui.schedule.job.biz.entity.JobInfo;
import cn.com.yuzhushui.schedule.job.biz.service.JobInfoService;
import cn.com.yuzhushui.schedule.job.enums.JobInfoEnum;

import com.alibaba.fastjson.JSONObject;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-12-27 14:53:19
 * @history
 */
@Service("jobInfoService")
public class JobInfoServiceImpl extends BaseServiceImpl<JobInfo,Integer> implements JobInfoService{
	
	@Autowired
	private JobInfoDao jobInfoDao;
	
	private Logger logger=LoggerFactory.getLogger(JobInfoServiceImpl.class);
	
	@Autowired
	private SchedulePackage schedulePackage;
	
	
	public static void main(String[] args){
	
	}

	public int addJobInfo(JobInfo jobInfo) {
		if(null==jobInfo) return 0;
		//Before insertion, it is judged whether or not there exists.。TODO 是否存在相同记录...
		JobInfo object=new JobInfo();
		object.setClassPath(jobInfo.getClassPath());
		object.setGroups(jobInfo.getGroups());
		List<JobInfo> jobInfoList= jobInfoDao.query(BeanUtil.pojoToMap(object));
		if(null!=jobInfoList && jobInfoList.size()>0){
			logger.info("===========>同一分组下不能出现同一任务，请重新输入。");
			return 0;
		}
		int count=jobInfoDao.insert(jobInfo);
		if(count>0){
			try {
				JobDetail jobDetail= schedulePackage.createJobDetailByJobInfo(jobInfo);
				Trigger trigger=schedulePackage.createCronTrigger(jobInfo);
				schedulePackage.scheduleJob(jobDetail, trigger);
			} catch (SchedulerException e) {
				logger.error("=========>新增job时出现异常，异常原因:{}",new Object[]{JSONObject.toJSONString(e)});
			}
		}
		return 0;
	}

	public int updateByJobInfo(JobInfo jobInfo) {
		if(null==jobInfo) return 0;
		int count=jobInfoDao.update(jobInfo);
		if(count>0){
			//如果，job的corn表达式，未变更，则不须要去更新。TODO 
			try {
				boolean exits=schedulePackage.containsJobDetail(jobInfo);
				if(jobInfo.getIsActivity().equals(JobInfoEnum.IS_ACTIVITY.ENABLED.getValue())){
					if(exits){
						//如果job的corn表达式有更新，则先删除旧的key，然后在新增..	TODO 
						Trigger trigger=schedulePackage.createCronTrigger(jobInfo);
						TriggerKey triggerKey= schedulePackage.getTriggerKeyByJobInfo(jobInfo);
						schedulePackage.rescheduleJob(triggerKey, trigger);
					}else{
						//如果没有schedulePackage中没有这个key，则须要新增之。TODO 
						JobDetail jobDetail= schedulePackage.createJobDetailByJobInfo(jobInfo);
						Trigger trigger=schedulePackage.createCronTrigger(jobInfo);
						schedulePackage.scheduleJob(jobDetail, trigger);
					}
				}else{
					//如果job设置的是禁用、则须要删除之。TODO 
					JobKey jobKey= schedulePackage.getJobKeyByJobInfo(jobInfo);
					schedulePackage.deleteJob(jobKey);
				}
			} catch (SchedulerException e) {
				logger.error("==================>系统异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
			}
		}
		return 0;
	}

	public int deleteJobInfoById(Integer id) {
		if(null==id || id.intValue()<1) return 0;
		JobInfo jobInfo=jobInfoDao.queryById(id);
		if(null==jobInfo) return 0;
		int count=jobInfoDao.delete(id);
		if(count>0){
			try {
				JobKey jobKey= schedulePackage.getJobKeyByJobInfo(jobInfo);
				schedulePackage.deleteJob(jobKey);
			} catch (SchedulerException e) {
				logger.error("==================>系统异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
			}
		}
		return 0;
	}
}