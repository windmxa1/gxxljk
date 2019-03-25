package org.model;

/**
 * ZRoleAuthority entity. @author MyEclipse Persistence Tools
 */

public class ZRoleAuthority implements java.io.Serializable {

	// Fields

	private Long id;
	private Long roleId;
	private Long authorityId;

	// Constructors

	/** default constructor */
	public ZRoleAuthority() {
	}

	/** full constructor */
	public ZRoleAuthority(Long roleId, Long authorityId) {
		this.roleId = roleId;
		this.authorityId = authorityId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getAuthorityId() {
		return this.authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

}