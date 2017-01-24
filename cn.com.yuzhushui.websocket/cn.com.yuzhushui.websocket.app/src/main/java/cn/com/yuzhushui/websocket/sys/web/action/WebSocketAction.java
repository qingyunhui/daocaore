package cn.com.yuzhushui.websocket.sys.web.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月20日下午3:01:51
 **/
@Controller
@RequestMapping(WebSocketAction.ACTION_PATH)
public class WebSocketAction {
	
	public static final String ACTION_PATH="/";
	
	Logger logger=LoggerFactory.getLogger(WebSocketAction.class);
	
	/*@RequestMapping(value="socketService", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView toIndex(){
		ModelAndView mv=new ModelAndView();
		
		logger.info("toindex....");
		
		mv.setViewName("index");
		return mv;
	}*/

}
