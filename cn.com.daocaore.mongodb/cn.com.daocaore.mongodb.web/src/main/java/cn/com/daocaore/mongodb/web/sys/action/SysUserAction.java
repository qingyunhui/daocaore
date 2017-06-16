package cn.com.daocaore.mongodb.web.sys.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.daocaore.mongodb.common.beans.DataTableInfo;
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
	public List<SysUser> queryByName(SysUser sysUser) {
		List<SysUser> sysAccountList= sysUserService.query(sysUser);
		return sysAccountList;
	}
	
	@RequestMapping("/getById")
	@ResponseBody
	public SysUser getById(String id) {
		SysUser sysUser= sysUserService.getById(id, SysUser.class);
		return sysUser;
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public int deleteById(String id) {
		return sysUserService.deleteById(id, SysUser.class);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public int save() {
		SysUser user=new SysUser();
		Random rd=new Random();
		int rdnum=rd.nextInt(9999999);
		user.setName(rdnum+"");
		user.setComments("fuck you"+rdnum);
		user.setEmail(rdnum+"@qq.com");
		return sysUserService.insert(user);
	}
	
	@RequestMapping("/saveBatch")
	@ResponseBody
	public List<SysUser> saveBatch() {
		List<SysUser> userList=new ArrayList<SysUser>();
		for(int i=0;i<5;i++){
			SysUser user=new SysUser();
			Random rd=new Random();
			int rdnum=rd.nextInt(9999999);
			user.setName(rdnum+"");
			user.setComments("hello word ! "+rdnum);
			user.setEmail(rdnum+"@163.com");
			userList.add(user);
		}
		return sysUserService.insertBatch(userList);
	}
	
	@RequestMapping("/deleteByIdWithName")
	@ResponseBody
	public int deleteByIdWithName(SysUser user){
		return sysUserService.delete(user);
	}
	
	@RequestMapping("/queryByCount")
	@ResponseBody
	public long queryByCount(SysUser user) {
		return sysUserService.queryCount(user);
	}
	
	@RequestMapping("/queryPage")
	@ResponseBody
	public DataTableInfo<SysUser> queryPage(HttpServletRequest request,SysUser user) {
		DataTableInfo<SysUser> dataTableInfo=sysUserService.queryPage(request, user);
		return dataTableInfo;
	}

}
