package com.tw.entity.sys;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles_permission_rel", catalog = "twq")
public class RolesPermissionRel implements java.io.Serializable {

	private static final long serialVersionUID = -319841937478048191L;
	private RolesPermissionRelId id;

	public RolesPermissionRel() {}

	public RolesPermissionRel(RolesPermissionRelId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "roleId", column = @Column(name = "role_id", nullable = false)),
			@AttributeOverride(name = "permissionId", column = @Column(name = "permission_id", nullable = false)) })
	public RolesPermissionRelId getId() {
		return this.id;
	}

	public void setId(RolesPermissionRelId id) {
		this.id = id;
	}

}