package org.model;

/**
 * ZAlarmDesc entity. @author MyEclipse Persistence Tools
 */

public class ZAlarmDesc implements java.io.Serializable {

	// Fields

	private Long id;
	private Integer level;
	private String description;

	// Constructors

	/** default constructor */
	public ZAlarmDesc() {
	}

	/** full constructor */
	public ZAlarmDesc(Integer level, String description) {
		this.level = level;
		this.description = description;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}