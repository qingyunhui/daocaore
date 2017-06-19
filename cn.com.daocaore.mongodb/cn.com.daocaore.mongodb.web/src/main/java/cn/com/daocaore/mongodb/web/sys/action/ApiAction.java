package cn.com.daocaore.mongodb.web.sys.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.daocaore.mongodb.sys.entity.SysAccount;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月19日上午9:18:07
 **/
@Controller
@RequestMapping(ApiAction.ACTION_PATH)
public class ApiAction {
	
	protected static final String ACTION_PATH="/api";
	
	@RequestMapping("/list")
	public String list(SysAccount sysAccount) {
		return ACTION_PATH+"/list";
	}
	
	@RequestMapping("/index")
	public String index(SysAccount sysAccount) {
		return ACTION_PATH+"/index";
	}


}
