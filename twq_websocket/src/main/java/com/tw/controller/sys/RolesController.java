package com.tw.controller.sys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tw.entity.sys.Permission;
import com.tw.entity.sys.Roles;
import com.tw.entity.sys.RolesPermissionRel;
import com.tw.entity.sys.RolesPermissionRelId;
import com.tw.interceptor.Perm;
import com.tw.service.sys.PermissionService;
import com.tw.service.sys.RolesPermissionRelService;
import com.tw.service.sys.RolesService;
import com.tw.service.sys.UserRoleRelService;
import com.tw.util.BaseUtil;
import com.tw.util.DataGrid;
import com.tw.util.Json;
import com.tw.vo.sys.PermissionVo;
import com.tw.vo.sys.RoleVo;

@Controller
@RequestMapping("/roles")
public class RolesController {

	@Autowired
	private RolesService rolesService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RolesPermissionRelService rolesPermissionRelService;
	@Autowired
	private UserRoleRelService userRoleRelService;
	
	@RequestMapping("/list")
	@ResponseBody
	public DataGrid<Roles> list(RoleVo rVo) {
		return rolesService.getScrollData(rVo.getPage(), rVo.getRows());
	}
	@RequestMapping("/getAll")
	@ResponseBody
	public List<RoleVo> getAll() {
		List<Roles> roles = rolesService.getListEntitys();
		List<RoleVo> roleVos = new ArrayList<RoleVo>();
		for (Roles roles2 : roles) {
			roleVos.add(new RoleVo(roles2.getRoleId(), roles2.getRoleName()));
		}
		return roleVos;
	}
	@Perm(privilegeValue="roleAdd")
	@RequestMapping("/addUI")
	public String addUI(Model model) {
		List<Object> list = permissionService.getObjects("o.permissionModule", "o.permissionModule");
		List<PermissionVo> pVos = new ArrayList<PermissionVo>();
		for (int i = 0,h=list.size(); i < h; i++) {
			List<Permission> ps = permissionService.findByProperty("permissionModule", (String)list.get(i));
			pVos.add(new PermissionVo((String)list.get(i), ps));
		}
		model.addAttribute("permissions", pVos);
		return "admin/role_add";
	}
	@Perm(privilegeValue="roleAdd")
	@ResponseBody
	@RequestMapping("/add")
	public Json add(RoleVo rVo) {
		Json json = new Json();
		Roles r = null;
		int id = 0;
		if (BaseUtil.isEmpty(rVo.getRoleId())) {
			r = rolesService.find(rVo.getRoleId());
			id = rVo.getRoleId();
		}else {
			r = new Roles();
			id = rolesService.getsysId("roleId")+1;
			r.setRoleId(id);
		}
		r.setRoleName(rVo.getRoleName());
		try {
			if (BaseUtil.isEmpty(rVo.getRoleId())) {
				rolesPermissionRelService.deleteByRoleId(id);
				rolesService.update(r);
			}else {
				rolesService.save(r);
			}
			Integer[] pids = rVo.getPermissionIds();
			for (Integer pid : pids) {
				RolesPermissionRel rp = new RolesPermissionRel(new RolesPermissionRelId(id, pid));
				rolesPermissionRelService.save(rp);
			}
			json.setMsg("角色添加/修改成功");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("角色添加/修改失败");
			e.printStackTrace();
		}
		return json;
	}
	@Perm(privilegeValue="roleEdit")
	@RequestMapping("/editUI")
	public String editUI(Integer roleId,Model model) {
		Roles roles = rolesService.find(roleId);
		model.addAttribute("roles", roles);
		List<RolesPermissionRel> rps = rolesPermissionRelService.findByProperty("id.roleId", roleId);
		model.addAttribute("rps", rps);
		
		List<Object> list = permissionService.getObjects("o.permissionModule", "o.permissionModule");
		List<PermissionVo> pVos = new ArrayList<PermissionVo>();
		for (int i = 0,h=list.size(); i < h; i++) {
			List<Permission> ps = permissionService.findByProperty("permissionModule", (String)list.get(i));
			pVos.add(new PermissionVo((String)list.get(i), ps));
		}
		model.addAttribute("permissions", pVos);
		return "admin/role_add";
	}
	@Perm(privilegeValue="roleDelete")
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer roleId) {
		Json json = new Json();
		if (userRoleRelService.exsit("id.roleId", roleId)) {
			json.setMsg("该角色不能删除，已经有人在使用");
			return json;
		}
		try {
			rolesService.delete(roleId);
			rolesPermissionRelService.deleteByRoleId(roleId);
			json.setMsg("角色删除成功");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("角色删除失败");
			e.printStackTrace();
		}
		return json;
	}
}
