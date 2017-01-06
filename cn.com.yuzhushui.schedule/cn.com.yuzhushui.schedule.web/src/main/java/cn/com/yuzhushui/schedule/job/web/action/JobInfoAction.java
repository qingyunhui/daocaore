package cn.com.yuzhushui.schedule.job.web.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.yuzhushui.schedule.common.base.BaseAction;
import cn.com.yuzhushui.schedule.job.biz.entity.JobInfo;
import cn.com.yuzhushui.schedule.job.biz.service.JobInfoService;
import cn.com.yuzhushui.schedule.job.web.vo.JobInfoForm;

import com.alibaba.fastjson.JSONObject;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-12-27 14:53:19
 * @history
 */
@Controller
@RequestMapping(JobInfoAction.ACTION_PATH)
public class JobInfoAction extends BaseAction<JobInfo, JobInfoForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(JobInfoAction.class);
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/job/jobInfo";
	
	@Autowired
	private JobInfoService jobInfoService;
	
	@RequestMapping(value = "list", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView list() {
		ModelAndView mv=new ModelAndView();
		Map<String,Object> map=new HashMap<String, Object>();
		List<JobInfo> jobInfoList=jobInfoService.query(map);
		
		if(null!=jobInfoList && jobInfoList.size()>0){
			for(JobInfo jobinfo:jobInfoList){
				logger.info("jobinfo={}",new Object[]{JSONObject.toJSONString(jobinfo)});
			}
		}
		
		logger.info("================>list.size({})",new Object[]{jobInfoList.size()});
		mv.setViewName(ACTION_PATH+"/list.htm");
		return mv;
	}
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}