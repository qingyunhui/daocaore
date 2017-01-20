package com.tw.service.sys;

import com.tw.dao.base.BaseDAO;
import com.tw.entity.sys.RolesPermissionRel;

public interface RolesPermissionRelService extends BaseDAO<RolesPermissionRel>{

	/**
	 * 根据角色ID删除中间表数据
	 * @param roleId
	 */
	public void deleteByRoleId(Integer roleId);
	/**
	 * 删除权限时会删除所有中间表数据
	 * @param permissionId
	 */
	public void deleteByPermissionId(Integer permissionId);
}
