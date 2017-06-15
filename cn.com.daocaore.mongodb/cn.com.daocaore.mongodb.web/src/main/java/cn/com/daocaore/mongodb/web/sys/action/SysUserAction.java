package cn.com.daocaore.mongodb.web.sys.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.daocaore.mongodb.sys.entity.SysUser;
import cn.com.daocaore.mongodb.sys.service.SysUserService;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月14日下午5:43:36
 **/
@Controller
@RequestMapping(SysUserAction.ACTION_PATH)
public class SysUserAction {
	
	protected static final String ACTION_PATH="/sys/sysUser";
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/queryByName")
	@ResponseBody
	public List<SysUser> queryByName(String name) {
		List<SysUser> sysUserList=sysUserService.queryByName(name);
		System.out.println("查找的用户有："+sysUserList.size()+"个.");
		return sysUserList;
	}
	
	@RequestMapping("/updateById")
	@ResponseBody
	public List<SysUser> updateById(String id) {
		
		SysUser sysUser=new SysUser();
		sysUser.setUserId("594222482b8ae233dc583d03");
		sysUser.setEmail("aaaaaaaa@qq.com");
		sysUser.setComments("comment..xxx");
		sysUserService.update(sysUser);
		
		return null;
	}
	
	@RequestMapping("/deleteByAccountId")
	@ResponseBody
	public String deleteByAccountId(Integer id) {
		sysUserService.deleteByAccountId(id);
		return "success";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public  String save() {
		SysUser user = new SysUser();
		Random random=new Random();
		int randomNum=random.nextInt(999999999);
		user.setAccountId(randomNum);
		user.setComments("www.good.com"+randomNum);
		user.setTest("hello good.");
		user.setWechat("good");
		user.setAge(33);
		user.setCtime(new Date());
		user.setEmail("good@qq.com");
		user.setUserId(999888777+"good");
		sysUserService.saveOrUpdate(user);
		return JSONObject.toJSONString(user);
	}
	
	@RequestMapping("/saveBatch")
	@ResponseBody
	public  String saveBatch() {
		Random random=new Random();
		List<SysUser> sysUserList=new ArrayList<SysUser>();
		for(int i=0;i<3;i++){
			SysUser user = new SysUser();
			int randomNum=random.nextInt(999999999);
			user.setAccountId(randomNum);
			user.setComments("www.taobao.com"+randomNum);
			user.setTest("hello taobao.");
			user.setWechat("miss li"+i);
			user.setAge(18+i);
			user.setCtime(new Date());
			user.setEmail("123456@qq.com"+i);
			sysUserList.add(user);
		}
		sysUserService.saveBatch(sysUserList);
		return JSONObject.toJSONString(sysUserList);
	}

}
