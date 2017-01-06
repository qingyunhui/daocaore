package cn.com.yuzhushui.schedule.job.biz.service;

import cn.com.yuzhushui.schedule.common.base.BaseService;
import cn.com.yuzhushui.schedule.job.biz.entity.JobInfo;
/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-12-27 14:53:19
 * @history
 */
public interface JobInfoService extends BaseService<JobInfo,Integer>{
	
	/***
	 * <p>新增</p>
	 * @param model 待新增的对象
	 * @param autoFil 是否自动填充用户信息..
	 * @return int 受影响的行数
	 * */
	public int addJobInfo(JobInfo jobInfo);
    
	
	public int updateByJobInfo(JobInfo jobInfo);
	
	public int deleteJobInfoById(Integer id);
}