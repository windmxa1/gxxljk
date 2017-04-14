package org.model;

/**
 * ZAuthority entity. @author MyEclipse Persistence Tools
 */

public class ZAuthority implements java.io.Serializable {

	// Fields

	private Long id;
	private String action;
	private String alias;

	// Constructors

	/** default constructor */
	public ZAuthority() {
	}

	/** full constructor */
	public ZAuthority(String action, String alias) {
		this.action = action;
		this.alias = alias;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}