package cn.com.daocaore.monitor.sys.web.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月25日下午2:21:59
 **/
@Controller
@RequestMapping(ImageAction.ACTION_PATH)
public class ImageAction {
	
	protected Logger logger=LoggerFactory.getLogger(ImageAction.class);
	protected static final String ACTION_PATH = "/image";
	
	@RequestMapping(value = "/demo")
	public ModelAndView demo() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/demo");
		return modelView;
	}

}
