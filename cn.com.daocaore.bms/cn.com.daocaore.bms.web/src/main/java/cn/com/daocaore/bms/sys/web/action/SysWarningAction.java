package cn.com.daocaore.bms.sys.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.com.daocaore.bms.common.base.BaseQuery;
import cn.com.daocaore.bms.sys.biz.entity.SysWarning;
import cn.com.daocaore.bms.sys.biz.service.SysWarningService;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月15日下午9:17:35
 **/
@Controller
@RequestMapping(SysWarningAction.ACTION_PATH)
public class SysWarningAction {
	
	protected Logger logger=LoggerFactory.getLogger(SysWarningAction.class);
	protected static final String ACTION_PATH = "/sys/warning";
	
	@Autowired
	private SysWarningService sysWarningService ;
	
	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/list");
		return modelView;
	}
	
	@RequestMapping(value = "/doList")
	@ResponseBody
	public PageInfo<SysWarning> doList(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
	
		PageInfo<SysWarning> pageInfo= sysWarningService.queryPage(new BaseQuery());
		logger.info("pageInfo",new Object[]{JSONObject.toJSONString(pageInfo)});
		return pageInfo;
	}

}
