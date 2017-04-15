package org.model;

/**
 * ZUserBelong entity. @author MyEclipse Persistence Tools
 */

public class ZUserBelong implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private String belong;

	// Constructors

	/** default constructor */
	public ZUserBelong() {
	}

	/** full constructor */
	public ZUserBelong(Long userId, String belong) {
		this.userId = userId;
		this.belong = belong;
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

	public String getBelong() {
		return this.belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

}