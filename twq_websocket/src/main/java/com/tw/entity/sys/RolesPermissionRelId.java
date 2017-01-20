package com.tw.entity.sys;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RolesPermissionRelId implements java.io.Serializable {

	private static final long serialVersionUID = -5246940932270693582L;
	private Integer roleId;
	private Integer permissionId;

	public RolesPermissionRelId() {}

	public RolesPermissionRelId(Integer roleId, Integer permissionId) {
		this.roleId = roleId;
		this.permissionId = permissionId;
	}

	@Column(name = "role_id", nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "permission_id", nullable = false)
	public Integer getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RolesPermissionRelId))
			return false;
		RolesPermissionRelId castOther = (RolesPermissionRelId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this
				.getRoleId() != null && castOther.getRoleId() != null && this
				.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getPermissionId() == castOther.getPermissionId()) || (this
						.getPermissionId() != null
						&& castOther.getPermissionId() != null && this
						.getPermissionId().equals(castOther.getPermissionId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37
				* result
				+ (getPermissionId() == null ? 0 : this.getPermissionId()
						.hashCode());
		return result;
	}

}