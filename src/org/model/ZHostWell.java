package org.model;

/**
 * ZHostWell entity. @author MyEclipse Persistence Tools
 */

public class ZHostWell implements java.io.Serializable {

	// Fields

	private Long id;
	private Long wellId;
	private Long hostId;

	// Constructors

	/** default constructor */
	public ZHostWell() {
	}

	/** full constructor */
	public ZHostWell(Long wellId, Long hostId) {
		this.wellId = wellId;
		this.hostId = hostId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWellId() {
		return this.wellId;
	}

	public void setWellId(Long wellId) {
		this.wellId = wellId;
	}

	public Long getHostId() {
		return this.hostId;
	}

	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}

}