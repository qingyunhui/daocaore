package cn.com.yuzhushui.schedule.common.Interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import qing.yun.hui.common.annotations.RepeatSubmitAnno;
import qing.yun.hui.common.constants.Constant;

/***
 ** @category 表单重复提交拦截器
 *<p>
 * 如果是post提交（非ajax提交），就没有必要使用拦截器，直接在提交表单后、redirect 到目标页面即可。
 * 而如果是ajax提交方式，就不能使用redirect、就必须使用该拦截器来实现。
 *<p>
 *<P>
 * 使用介绍，在add(xx)action中 添加 @RepeatSubmitAnno(save=true)并且在期add页面在中添加隐藏域、
 * <input type="hidden" name="token" value="${token}">
 * 注意这里的token是在拦截器中生成的，必须与token中变量名保持一致。
 * 然后在,doAdd(xx)action中添加 @RepeatSubmitAnno(remove=true)，即可。
 *</p>
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月5日上午10:31:01
 **/
public class RepeatSubmitInterceptor extends HandlerInterceptorAdapter {
	
	Logger logger=LoggerFactory.getLogger(RepeatSubmitInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		if(handler instanceof HandlerMethod){
			HandlerMethod hm=(HandlerMethod)handler;
			Method method=hm.getMethod();
			RepeatSubmitAnno rsa=method.getAnnotation(RepeatSubmitAnno.class);
			if(null!=rsa){
				boolean save=rsa.save();
				if(save){
					//生成一个token唯一标识码、存储到session中。
					String token=UUID.randomUUID().toString();
					request.getSession().setAttribute(Constant.TOKEN, token);
				}
				boolean remove=rsa.remove();
				if(remove){
					if(isRepeatSubmit(request)){
						logger.error("==========>请不要重复提交表单。<==========");
						return false;
					}
					request.getSession().removeAttribute(Constant.TOKEN);
				}
			}
			return true;
		}else{
			return super.preHandle(request, response, handler);
		}
	}
	
	 /**
	  * <p>校验是否重复提交表单</p>
	  * @param request
	  * @return true or false
	  * */
	 protected boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession().getAttribute(Constant.TOKEN);
        if (null==serverToken) {
            return true;
        }
        String clinetToken = request.getParameter(Constant.TOKEN);
        if (null==clinetToken) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }
	
}
