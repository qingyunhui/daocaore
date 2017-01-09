package cn.com.yuzhushui.schedule.job.web.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
	protected static final String ACTION_PATH="/sys/jobInfo";
	
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
		mv.setViewName(ACTION_PATH+"/list");
		return mv;
	}
	
	@RequestMapping(value = "add")
	public ModelAndView add(JobInfoForm form) {
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/add");
		modelAndView.addObject("jobInfoForm", form);
		return modelAndView;
	}
	
	@RequestMapping(value = "doAdd2", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView doAdd2(JobInfoForm form) {
		ModelAndView mv=new ModelAndView();
		JobInfo jobInfo=new JobInfo();
		try {
			BeanUtils.copyProperties(form,jobInfo);
			int count=jobInfoService.addJobInfo(jobInfo);
			logger.info("=====受影响的行数:{}条。",new Object[]{count});
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName(ACTION_PATH+"/list");
		return mv;
	}
		
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}