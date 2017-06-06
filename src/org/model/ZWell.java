package org.model;

/**
 * ZWell entity. @author MyEclipse Persistence Tools
 */

public class ZWell implements java.io.Serializable {

	// Fields

	private Long id;
	private Long hostId;
	private String name;
	private String location;
	private String lat;
	private String lon;
	private Integer distance;
	private Long createTime;
	private Integer range;

	// Constructors

	/** default constructor */
	public ZWell() {
	}

	/** minimal constructor */
	public ZWell(Long hostId, String location, Integer distance, Long createTime) {
		this.hostId = hostId;
		this.location = location;
		this.distance = distance;
		this.createTime = createTime;
	}

	/** full constructor */
	public ZWell(Long hostId, String name, String location, String lat,
			String lon, Integer distance, Long createTime, Integer range) {
		this.hostId = hostId;
		this.name = name;
		this.location = location;
		this.lat = lat;
		this.lon = lon;
		this.distance = distance;
		this.createTime = createTime;
		this.range = range;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHostId() {
		return this.hostId;
	}

	public void setHostId(Long hostId) {
		this.hostId = hostId;
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

	public Integer getDistance() {
		return this.distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Integer getRange() {
		return this.range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}

}