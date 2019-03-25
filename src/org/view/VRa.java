package org.view;

/**
 * VRa entity. @author MyEclipse Persistence Tools
 */

public class VRa implements java.io.Serializable {

	// Fields

	private VRaId id;

	// Constructors

	/** default constructor */
	public VRa() {
	}

	/** full constructor */
	public VRa(VRaId id) {
		this.id = id;
	}

	// Property accessors

	public VRaId getId() {
		return this.id;
	}

	public void setId(VRaId id) {
		this.id = id;
	}

}