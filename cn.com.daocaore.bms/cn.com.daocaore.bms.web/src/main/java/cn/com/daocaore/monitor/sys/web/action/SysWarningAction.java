package cn.com.daocaore.monitor.sys.web.action;

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

import cn.com.daocaore.monitor.common.base.BaseQuery;
import cn.com.daocaore.monitor.common.base.ResponseData;
import cn.com.daocaore.monitor.common.bean.DataTableInfo;
import cn.com.daocaore.monitor.common.constant.Constant;
import cn.com.daocaore.monitor.sys.biz.entity.SysWarning;
import cn.com.daocaore.monitor.sys.biz.service.SysWarningService;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

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
	public ModelAndView list(SysWarning sysWarning) {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/list");
		modelView.addObject(Constant.ENTITY, sysWarning);
		return modelView;
	}
	
	@RequestMapping(value = "/doList")
	@ResponseBody
	public PageInfo<SysWarning> doList(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		PageInfo<SysWarning> pageInfo= sysWarningService.queryPage(new BaseQuery());
		logger.info("pageInfo",new Object[]{JSONObject.toJSONString(pageInfo)});
		return pageInfo;
	}
	
	@RequestMapping(value = "/doDataTablePage")
	@ResponseBody
	public DataTableInfo<SysWarning> doDataTablePage(HttpServletRequest request,SysWarning sysWarning) {
		DataTableInfo<SysWarning> dataTableInfo= sysWarningService.queryPages(request,sysWarning);
		return dataTableInfo;
	}

	@RequestMapping(value = "/doDelete")
	@ResponseBody
	public ResponseData doDelete(Integer id) {
		int count=sysWarningService.delete(id);
		ResponseData rd=new ResponseData(count>0?"处理成功!":"处理失败!");
		rd.put("code", 200);
		return rd;
	}
	
	@RequestMapping(value = "/doUpdate")
	@ResponseBody
	public ResponseData doUpdate(SysWarning sysWarning) {
		int count=sysWarningService.update(sysWarning);
		ResponseData rd=new ResponseData(count>0?"处理成功!":"处理失败!");
//		rd.put("code", 200);
		return rd;
	}
	
}
