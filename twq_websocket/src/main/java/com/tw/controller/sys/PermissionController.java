package com.tw.controller.sys;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tw.entity.sys.Permission;
import com.tw.interceptor.Perm;
import com.tw.service.sys.PermissionService;
import com.tw.service.sys.RolesPermissionRelService;
import com.tw.util.BaseUtil;
import com.tw.util.DataGrid;
import com.tw.util.Json;
import com.tw.vo.sys.PermissionVo;
/**
 * 权限管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RolesPermissionRelService rolesPermissionRelService;
	
	@Perm(privilegeValue="permissionList")
	@RequestMapping("/list")
	@ResponseBody
	public DataGrid<Permission> list(PermissionVo pVo) {
		return permissionService.getScrollData(pVo.getPage(), pVo.getRows());
	}
	@Perm(privilegeValue="permissionAdd")
	@RequestMapping("/add")
	@ResponseBody
	public Json add(PermissionVo pVo) {
		Permission permission = null;
		if (BaseUtil.isEmpty(pVo.getPermissionId())) {
			permission = permissionService.find(pVo.getPermissionId());
			permission.setPermissionCode(pVo.getPermissionCode());
			permission.setPermissionModule(pVo.getPermissionModule());
			permission.setPermissionName(pVo.getPermissionName());
		}else {
			permission = new Permission();
			BeanUtils.copyProperties(pVo, permission);
		}
		Json json = new Json();
		try {
			permissionService.update(permission);
			json.setMsg("权限添加/修改成功");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("权限添加/修改失败");
			e.printStackTrace();
		}
		return json;
	}
	@Perm(privilegeValue="permissionEdit")
	@RequestMapping("/editUI")
	public String editUI(Integer permissionId,Model model) {
		Permission p = permissionService.find(permissionId);
		model.addAttribute("p", p);
		return "admin/permission_add";
	}
	
	@Perm(privilegeValue="permissionDelete")
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer permissionId) {
		Json json = new Json();
		try {
			rolesPermissionRelService.deleteByPermissionId(permissionId);
			permissionService.delete(permissionId);
			json.setMsg("权限删除成功");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("权限删除失败");
			e.printStackTrace();
		}
		return json;
	}
	@RequestMapping("/init")
	public void init() {
		Permission p = new Permission();
		p.setPermissionCode("permissionList");
		p.setPermissionModule("sys");
		p.setPermissionName("权限查询");
		permissionService.save(p);
		
		Permission p1 = new Permission();
		p1.setPermissionCode("permissionAdd");
		p1.setPermissionModule("sys");
		p1.setPermissionName("权限添加");
		permissionService.save(p1);
		
		Permission p2 = new Permission();
		p2.setPermissionCode("permissionEdit");
		p2.setPermissionModule("sys");
		p2.setPermissionName("权限修改");
		permissionService.save(p2);
		
		Permission p3 = new Permission();
		p3.setPermissionCode("permissionDelete");
		p3.setPermissionModule("sys");
		p3.setPermissionName("权限删除");
		permissionService.save(p3);
		
		
		Permission p4 = new Permission();
		p4.setPermissionCode("menuList");
		p4.setPermissionModule("sys");
		p4.setPermissionName("菜单查询");
		permissionService.save(p4);
		
		Permission p5 = new Permission();
		p5.setPermissionCode("menuAdd");
		p5.setPermissionModule("sys");
		p5.setPermissionName("菜单添加");
		permissionService.save(p5);
		
		Permission p6 = new Permission();
		p6.setPermissionCode("menuEdit");
		p6.setPermissionModule("sys");
		p6.setPermissionName("菜单修改");
		permissionService.save(p6);
		
		Permission p7 = new Permission();
		p7.setPermissionCode("menuDelete");
		p7.setPermissionModule("sys");
		p7.setPermissionName("菜单删除");
		permissionService.save(p7);
		
		
		Permission p8 = new Permission();
		p8.setPermissionCode("roleList");
		p8.setPermissionModule("sys");
		p8.setPermissionName("角色查询");
		permissionService.save(p8);
		
		Permission p9 = new Permission();
		p9.setPermissionCode("roleAdd");
		p9.setPermissionModule("sys");
		p9.setPermissionName("角色添加");
		permissionService.save(p9);
		
		Permission p10 = new Permission();
		p10.setPermissionCode("roleEdit");
		p10.setPermissionModule("sys");
		p10.setPermissionName("角色修改");
		permissionService.save(p10);
		
		Permission p11 = new Permission();
		p11.setPermissionCode("roleDelete");
		p11.setPermissionModule("sys");
		p11.setPermissionName("角色删除");
		permissionService.save(p11);
		
		
		Permission p12 = new Permission();
		p12.setPermissionCode("userList");
		p12.setPermissionModule("sys");
		p12.setPermissionName("用户查询");
		permissionService.save(p12);
		
		Permission p13 = new Permission();
		p13.setPermissionCode("userAdd");
		p13.setPermissionModule("sys");
		p13.setPermissionName("用户添加");
		permissionService.save(p13);
		
		Permission p14 = new Permission();
		p14.setPermissionCode("userEdit");
		p14.setPermissionModule("sys");
		p14.setPermissionName("用户修改");
		permissionService.save(p14);
		
		Permission p15 = new Permission();
		p15.setPermissionCode("userDelete");
		p15.setPermissionModule("sys");
		p15.setPermissionName("用户删除");
		permissionService.save(p15);
		
		Permission p16 = new Permission();
		p16.setPermissionCode("userRole");
		p16.setPermissionModule("sys");
		p16.setPermissionName("用户角色授权");
		permissionService.save(p16);
		
		Permission p17 = new Permission();
		p17.setPermissionCode("userMenu");
		p17.setPermissionModule("sys");
		p17.setPermissionName("用户菜单授权");
		permissionService.save(p17);
	}
}
