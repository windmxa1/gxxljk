package org.model;

/**
 * ZAlarm entity. @author MyEclipse Persistence Tools
 */

public class ZAlarm implements java.io.Serializable {

	// Fields

	private Long id;
	private Long occurTime;
	private String distance;
	private String description;
	private String level;
	private Integer ack;
	private String lastTime;

	// Constructors

	/** default constructor */
	public ZAlarm() {
	}

	/** full constructor */
	public ZAlarm(Long occurTime, String distance, String description,
			String level, Integer ack, String lastTime) {
		this.occurTime = occurTime;
		this.distance = distance;
		this.description = description;
		this.level = level;
		this.ack = ack;
		this.lastTime = lastTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOccurTime() {
		return this.occurTime;
	}

	public void setOccurTime(Long occurTime) {
		this.occurTime = occurTime;
	}

	public String getDistance() {
		return this.distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getAck() {
		return this.ack;
	}

	public void setAck(Integer ack) {
		this.ack = ack;
	}

	public String getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

}