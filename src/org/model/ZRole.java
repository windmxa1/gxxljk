package org.model;

/**
 * ZRole entity. @author MyEclipse Persistence Tools
 */

public class ZRole implements java.io.Serializable {

	// Fields

	private Long id;
	private String role;

	// Constructors

	/** default constructor */
	public ZRole() {
	}

	/** full constructor */
	public ZRole(String role) {
		this.role = role;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}