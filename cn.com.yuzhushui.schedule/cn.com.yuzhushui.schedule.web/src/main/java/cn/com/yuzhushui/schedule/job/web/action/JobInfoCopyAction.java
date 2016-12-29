package cn.com.yuzhushui.schedule.job.web.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yuzhushui.schedule.common.base.BaseAction;
import cn.com.yuzhushui.schedule.job.biz.entity.JobInfoCopy;
import cn.com.yuzhushui.schedule.job.web.vo.JobInfoCopyForm;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-12-27 14:53:20
 * @history
 */
@Controller
@RequestMapping(JobInfoCopyAction.ACTION_PATH)
public class JobInfoCopyAction extends BaseAction<JobInfoCopy, JobInfoCopyForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(JobInfoCopyAction.class);
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/job/jobInfoCopy";
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}