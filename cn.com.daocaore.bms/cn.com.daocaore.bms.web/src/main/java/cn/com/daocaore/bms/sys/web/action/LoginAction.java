package cn.com.daocaore.bms.sys.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import qing.yun.hui.common.utils.CookieUtil;
import qing.yun.hui.common.utils.StringUtil;
import cn.com.daocaore.bms.common.bean.SessionInfo;
import cn.com.daocaore.bms.common.cache.ShardedJedisCached;
import cn.com.daocaore.bms.common.constant.Constant;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月5日下午4:25:38
 **/
@Controller
@RequestMapping(LoginAction.ACTION_PATH)
public class LoginAction {
	
	protected Logger logger=LoggerFactory.getLogger(LoginAction.class);
	protected static final String ACTION_PATH = "/sys";
	
	@Autowired
	private ShardedJedisCached shardedJedisCached;
	
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		/***
		 * @1.如果用户未注册则进入引导页...
		 * @2.用户已注册，判断用户是否是一个月内免登陆(数据库有标识字段)、查询redis缓存中是否存在这个标识，
		 *    @2.0判断登陆次数是否>N次
		 *    @2.1如果用户是一个月内免登陆、且还在有效期、则跳转到主页面。
		 *    @2.2如果用户不是一个月内免登陆、则跳转到登陆页面。
		 * */
		ModelAndView modelView = new ModelAndView(ACTION_PATH+"/login");
		String sessionId=CookieUtil.getCookieValueByName(request, Constant.SESSION_INFO);
		if (StringUtil.isEmpty(sessionId)) {
			return modelView;
		}
		String sessionInfoJson=shardedJedisCached.get(sessionId);
		SessionInfo sessInfoInfo=JSONObject.parseObject(sessionInfoJson, SessionInfo.class);
		if(null!=sessInfoInfo){
			modelView.setViewName("redirect:"+ACTION_PATH+"/index.htm");
			return modelView;
		}
		return modelView;
	}
	
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/index");
		return modelView;
	}
	
	@RequestMapping(value = "/chart")
	public ModelAndView chart(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/chart");
		return modelView;
	}

}
