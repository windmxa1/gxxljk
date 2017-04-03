package org.model;

/**
 * ZException entity. @author MyEclipse Persistence Tools
 */

public class ZException implements java.io.Serializable {

	// Fields

	private Long id;
	private Long time;
	private Integer ack;
	private String description;

	// Constructors

	/** default constructor */
	public ZException() {
	}

	/** full constructor */
	public ZException(Long time, Integer ack, String description) {
		this.time = time;
		this.ack = ack;
		this.description = description;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Integer getAck() {
		return this.ack;
	}

	public void setAck(Integer ack) {
		this.ack = ack;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}