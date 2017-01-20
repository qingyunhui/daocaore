package com.tw.service.sys.impl;

import org.springframework.stereotype.Service;

import com.tw.dao.base.BaseDAOSupport;
import com.tw.entity.sys.RolesPermissionRel;
import com.tw.service.sys.RolesPermissionRelService;
@Service("rolesPermissionRelService")
public class RolesPermissionRelServiceImpl extends BaseDAOSupport<RolesPermissionRel> implements RolesPermissionRelService{

	public void deleteByRoleId(Integer roleId) {
		em.createQuery("delete from RolesPermissionRel o where o.id.roleId=?1").setParameter(1, roleId).executeUpdate();
	}
	public void deleteByPermissionId(Integer permissionId) {
		em.createQuery("delete from RolesPermissionRel o where o.id.permissionId=?1").setParameter(1, permissionId).executeUpdate();
	}
}
