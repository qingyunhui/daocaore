package cn.com.daocaore.bms.test.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月3日下午5:12:29
 **/
@Controller
@RequestMapping(TestAction.ACTION_PATH)
public class TestAction {

	protected static final String ACTION_PATH = "/test";
	
	Logger logger=LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/hello")
	public ModelAndView hello(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		logger.info("*********** bms.success ***********");
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/hello");
		
		
		return modelView;
	}
	
}
