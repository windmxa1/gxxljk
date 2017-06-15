package org.model;

/**
 * ZWell entity. @author MyEclipse Persistence Tools
 */

public class ZWell implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String location;
	private String lat;
	private String lon;
	private Integer beginDistance;
	private Integer endDistance;
	private Long createTime;

	// Constructors

	/** default constructor */
	public ZWell() {
	}

	/** minimal constructor */
	public ZWell(String location, Integer beginDistance, Integer endDistance,
			Long createTime) {
		this.location = location;
		this.beginDistance = beginDistance;
		this.endDistance = endDistance;
		this.createTime = createTime;
	}

	/** full constructor */
	public ZWell(String name, String location, String lat, String lon,
			Integer beginDistance, Integer endDistance, Long createTime) {
		this.name = name;
		this.location = location;
		this.lat = lat;
		this.lon = lon;
		this.beginDistance = beginDistance;
		this.endDistance = endDistance;
		this.createTime = createTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLat() {
		return this.lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return this.lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public Integer getBeginDistance() {
		return this.beginDistance;
	}

	public void setBeginDistance(Integer beginDistance) {
		this.beginDistance = beginDistance;
	}

	public Integer getEndDistance() {
		return this.endDistance;
	}

	public void setEndDistance(Integer endDistance) {
		this.endDistance = endDistance;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

}