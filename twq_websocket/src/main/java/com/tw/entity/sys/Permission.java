package com.tw.entity.sys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
/**
 * 权限表
 * @author Administrator
 *
 */
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
public class Permission implements Serializable{

	private static final long serialVersionUID = -5313874612129904827L;
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
	
	@Id @GeneratedValue
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	@Column(name="permission_code",length=20,nullable=false,unique=true)
	public String getPermissionCode() {
		return permissionCode;
	}
	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	@Column(name="permission_chinese",length=30)
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	@Column(name="permission_module",length=20)
	public String getPermissionModule() {
		return permissionModule;
	}
	public void setPermissionModule(String permissionModule) {
		this.permissionModule = permissionModule;
	}
	
}
