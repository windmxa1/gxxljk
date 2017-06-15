package org.view;

/**
 * VWell entity. @author MyEclipse Persistence Tools
 */

public class VWell implements java.io.Serializable {

	// Fields

	private VWellId id;

	// Constructors

	/** default constructor */
	public VWell() {
	}

	/** full constructor */
	public VWell(VWellId id) {
		this.id = id;
	}

	// Property accessors

	public VWellId getId() {
		return this.id;
	}

	public void setId(VWellId id) {
		this.id = id;
	}

}