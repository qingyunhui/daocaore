package cn.com.daocaore.monitor.goods.web.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年11月26日下午6:01:13
 **/
@Controller
@RequestMapping(MerchandiseController.ACTION_PATH)
public class MerchandiseController {
	
	protected Logger logger=LoggerFactory.getLogger(MerchandiseController.class);
	protected static final String ACTION_PATH = "/goods/merchandise";

}
