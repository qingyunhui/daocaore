package cn.com.daocaore.mongodb.web.sys.action;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.daocaore.mongodb.sys.entity.SysUser;
import cn.com.daocaore.mongodb.sys.service.SysUserService;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月14日下午5:43:36
 **/
@Controller
@Scope("prototype")
@RequestMapping("sys")
public class SysUserAction {
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/queryByName")
	@ResponseBody
	public List<SysUser> queryByName(String name) {
		
		List<SysUser> sysUserList=sysUserService.queryByName(name);
		System.out.println("查找的用户有："+sysUserList.size()+"个.");
		
		return sysUserList;
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public  String save() {
		SysUser user = new SysUser();
		Random random=new Random(1000);
		int randomNum=random.nextInt(1000);
		user.setAccountId(100+randomNum+"");
		user.setComments("www.baidu.com"+randomNum);
//		user.setTest("hello word.");
		sysUserService.saveOrUpdate(user);
		return JSONObject.toJSONString(user);
	}

}
