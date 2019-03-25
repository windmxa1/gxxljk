package org.model;

/**
 * ZUserRole entity. @author MyEclipse Persistence Tools
 */

public class ZUserRole implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private Long roleId;

	// Constructors

	/** default constructor */
	public ZUserRole() {
	}

	/** full constructor */
	public ZUserRole(Long userId, Long roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}