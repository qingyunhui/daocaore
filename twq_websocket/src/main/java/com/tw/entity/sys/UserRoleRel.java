package com.tw.entity.sys;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户角色中间表
 */
@Entity
@Table(name = "USER_ROLE_REL")
public class UserRoleRel implements Serializable {

	private static final long serialVersionUID = 5349761623939219158L;
	private UserRoleRelId id;

	public UserRoleRel() {}

	public UserRoleRel(UserRoleRelId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false)),
			@AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", nullable = false)) })
	public UserRoleRelId getId() {
		return this.id;
	}

	public void setId(UserRoleRelId id) {
		this.id = id;
	}
}