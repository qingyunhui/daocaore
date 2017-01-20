package com.tw.entity.sys;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 角色(权限组)
 * @author Administrator
 *
 */
@Entity
public class Roles implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer roleId;
	private String roleName;
	
	public Roles(){}
	
	public Roles(Integer roleId) {
		this.roleId = roleId;
	}
	
	@Id
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	@Column(name="role_name",length=50)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
