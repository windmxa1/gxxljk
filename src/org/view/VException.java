package org.view;

/**
 * VException entity. @author MyEclipse Persistence Tools
 */

public class VException implements java.io.Serializable {

	// Fields

	private VExceptionId id;

	// Constructors

	/** default constructor */
	public VException() {
	}

	/** full constructor */
	public VException(VExceptionId id) {
		this.id = id;
	}

	// Property accessors

	public VExceptionId getId() {
		return this.id;
	}

	public void setId(VExceptionId id) {
		this.id = id;
	}

}