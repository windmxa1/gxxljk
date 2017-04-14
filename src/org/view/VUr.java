package org.view;

/**
 * VUr entity. @author MyEclipse Persistence Tools
 */

public class VUr implements java.io.Serializable {

	// Fields

	private VUrId id;

	// Constructors

	/** default constructor */
	public VUr() {
	}

	/** full constructor */
	public VUr(VUrId id) {
		this.id = id;
	}

	// Property accessors

	public VUrId getId() {
		return this.id;
	}

	public void setId(VUrId id) {
		this.id = id;
	}

}