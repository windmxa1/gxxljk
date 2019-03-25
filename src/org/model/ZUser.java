package org.model;

/**
 * ZUser entity. @author MyEclipse Persistence Tools
 */

public class ZUser implements java.io.Serializable {

	// Fields

	private Long id;
	private String username;
	private String password;

	// Constructors

	/** default constructor */
	public ZUser() {
	}

	/** full constructor */
	public ZUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}