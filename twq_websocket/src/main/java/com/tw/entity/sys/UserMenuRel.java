package com.tw.entity.sys;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户资源中间表
 */
@Entity
@Table(name = "USER_MENU_REL")
public class UserMenuRel implements Serializable{

	private static final long serialVersionUID = 7065944477320158611L;
	private UserMenuRelId id;

	public UserMenuRel() {}

	public UserMenuRel(UserMenuRelId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false)),
			@AttributeOverride(name = "menuId", column = @Column(name = "MENU_ID", nullable = false)) })
	public UserMenuRelId getId() {
		return id;
	}

	public void setId(UserMenuRelId id) {
		this.id = id;
	}
}
