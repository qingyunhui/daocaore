
package cn.com.daocaore.mongodb.web.sys.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: 
 * 
 * @Date Create on 2017年5月10日
 * @author <a href="mailto:qingyunhui@zuozh.com">qingyunhui</a>
 * @since version1.0 Copyright 2015 ZZJR All Rights Reserved.
 */
@Controller
public class IndexController {

	@RequestMapping("/")
	public String index(){
		return "index";		
	}
	
}
