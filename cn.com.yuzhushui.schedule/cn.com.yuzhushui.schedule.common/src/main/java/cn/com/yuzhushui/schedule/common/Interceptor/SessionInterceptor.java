package cn.com.yuzhushui.schedule.common.Interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import qing.yun.hui.common.constants.SymbolConstant;
import qing.yun.hui.common.utils.CookieUtil;
import qing.yun.hui.common.utils.StringUtil;
import cn.com.yuzhushui.schedule.cache.ShardedJedisCached;
import cn.com.yuzhushui.schedule.common.bean.SessionInfo;
import cn.com.yuzhushui.schedule.constant.ScheduleConstant;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category 拦截器
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月24日下午11:33:33
 **/
@Getter
@Setter
public class SessionInterceptor extends HandlerInterceptorAdapter{
	
	private String[] noInterceptors;	//不须要拦截的url
	
	private String[] registers;		   //须要注册的url
	
	private String[] logins;		 //须要登陆的url
	
	@Value("#{properties['loginPath']}")
	private String loginPath;
	
	@Autowired
	private ShardedJedisCached shardedJedisCached;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		String curUrl = request.getServletPath();
		if(null==noInterceptors) return super.preHandle(request, response, handler);
		curUrl=StringUtil.truncateBothCharact(curUrl, SymbolConstant.SLASH);
		for(String url:noInterceptors){
			if(curUrl.trim().equals(url.trim())){
				return super.preHandle(request, response, handler);
			}
		}
		
		String sessionId=CookieUtil.getCookieValueByName(request, ScheduleConstant.SESSION_INFO);
		if(StringUtil.isEmpty(sessionId)){
			String appLoginPath=getLoginPath(request);
			Cookie cookie=CookieUtil.getCookieByName(request, ScheduleConstant.SESSION_INFO);
			CookieUtil.deleteCookie(request, response, cookie, ScheduleConstant.DOMAIN, ScheduleConstant.ROOT_PATH);
			response.sendRedirect(appLoginPath);
			return false;
		}
		
		String sessionInfo= shardedJedisCached.get(sessionId);
		
		if(StringUtil.isEmpty(sessionInfo)){
			//删除cookie中的session
			String appLoginPath=getLoginPath(request);
			Cookie cookie=CookieUtil.getCookieByName(request, ScheduleConstant.SESSION_INFO);
			CookieUtil.deleteCookie(request, response, cookie, ScheduleConstant.DOMAIN, ScheduleConstant.ROOT_PATH);
			response.sendRedirect(appLoginPath);
			return false;
		}
		
		//用户在有操作时，实时更新cookie有效期..
		SessionInfo mySessionInfo=JSONObject.parseObject(sessionInfo, SessionInfo.class);
		if(null!=mySessionInfo){
			CookieUtil.setCookie(request, response, ScheduleConstant.SESSION_INFO, sessionId, ScheduleConstant.DOMAIN, ScheduleConstant.ROOT_PATH, ScheduleConstant.COOKIE_VALIDITY_TIME);
			return super.preHandle(request, response, handler);
		}
		
		return super.preHandle(request, response, handler);
	}

	/**
	 * <p>获取首页登陆url</p>
	 *@param request
	 *@param loginPath
	 *@return 获取登陆路径 
	 **/
	public String getLoginPath(HttpServletRequest request){
		String appLoginPath=null;
		String contextPath=request.getServletContext().getContextPath();
		if(!loginPath.startsWith(SymbolConstant.SLASH)){
			appLoginPath=contextPath+SymbolConstant.SLASH+loginPath;
		}else{
			appLoginPath=contextPath+loginPath;
		}
		return appLoginPath;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
	}
	
}
