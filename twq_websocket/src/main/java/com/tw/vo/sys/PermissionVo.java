package com.tw.vo.sys;

import java.util.List;

import com.tw.entity.sys.Permission;

public class PermissionVo {

	private Integer permissionId;
	/**
	 * 权限值
	 */
	private String permissionCode;
	/**
	 * 中文名称
	 */
	private String permissionName;
	/**
	 * 模块
	 */
	private String permissionModule;
	
	private List<Permission> permissions;
	
	private int page;
	private int rows;
	public PermissionVo() {}
	public PermissionVo(String permissionModule, List<Permission> permissions) {
		this.permissionModule = permissionModule;
		this.permissions = permissions;
	}
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	public String getPermissionCode() {
		return permissionCode;
	}
	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getPermissionModule() {
		return permissionModule;
	}
	public void setPermissionModule(String permissionModule) {
		this.permissionModule = permissionModule;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}
