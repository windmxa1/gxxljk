package org.model;

/**
 * ZLog entity. @author MyEclipse Persistence Tools
 */

public class ZLog implements java.io.Serializable {

	// Fields

	private Long id;
	private String username;
	private String operation;
	private Long time;
	private Long dataId;

	// Constructors

	/** default constructor */
	public ZLog() {
	}

	/** minimal constructor */
	public ZLog(String username, String operation, Long time) {
		this.username = username;
		this.operation = operation;
		this.time = time;
	}

	/** full constructor */
	public ZLog(String username, String operation, Long time, Long dataId) {
		this.username = username;
		this.operation = operation;
		this.time = time;
		this.dataId = dataId;
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

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Long getDataId() {
		return this.dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

}