package org.view;

/**
 * VWellId entity. @author MyEclipse Persistence Tools
 */

public class VWellId implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String location;
	private String lat;
	private String lon;
	private Integer beginDistance;
	private Integer endDistance;
	private Long createTime;
	private Long hostId;

	// Constructors

	/** default constructor */
	public VWellId() {
	}

	/** minimal constructor */
	public VWellId(Long id, String location, Integer beginDistance,
			Integer endDistance, Long createTime, Long hostId) {
		this.id = id;
		this.location = location;
		this.beginDistance = beginDistance;
		this.endDistance = endDistance;
		this.createTime = createTime;
		this.hostId = hostId;
	}

	/** full constructor */
	public VWellId(Long id, String name, String location, String lat,
			String lon, Integer beginDistance, Integer endDistance,
			Long createTime, Long hostId) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.lat = lat;
		this.lon = lon;
		this.beginDistance = beginDistance;
		this.endDistance = endDistance;
		this.createTime = createTime;
		this.hostId = hostId;
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

	public Long getHostId() {
		return this.hostId;
	}

	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VWellId))
			return false;
		VWellId castOther = (VWellId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getLocation() == castOther.getLocation()) || (this
						.getLocation() != null
						&& castOther.getLocation() != null && this
						.getLocation().equals(castOther.getLocation())))
				&& ((this.getLat() == castOther.getLat()) || (this.getLat() != null
						&& castOther.getLat() != null && this.getLat().equals(
						castOther.getLat())))
				&& ((this.getLon() == castOther.getLon()) || (this.getLon() != null
						&& castOther.getLon() != null && this.getLon().equals(
						castOther.getLon())))
				&& ((this.getBeginDistance() == castOther.getBeginDistance()) || (this
						.getBeginDistance() != null
						&& castOther.getBeginDistance() != null && this
						.getBeginDistance()
						.equals(castOther.getBeginDistance())))
				&& ((this.getEndDistance() == castOther.getEndDistance()) || (this
						.getEndDistance() != null
						&& castOther.getEndDistance() != null && this
						.getEndDistance().equals(castOther.getEndDistance())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this
						.getCreateTime() != null
						&& castOther.getCreateTime() != null && this
						.getCreateTime().equals(castOther.getCreateTime())))
				&& ((this.getHostId() == castOther.getHostId()) || (this
						.getHostId() != null && castOther.getHostId() != null && this
						.getHostId().equals(castOther.getHostId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getLocation() == null ? 0 : this.getLocation().hashCode());
		result = 37 * result
				+ (getLat() == null ? 0 : this.getLat().hashCode());
		result = 37 * result
				+ (getLon() == null ? 0 : this.getLon().hashCode());
		result = 37
				* result
				+ (getBeginDistance() == null ? 0 : this.getBeginDistance()
						.hashCode());
		result = 37
				* result
				+ (getEndDistance() == null ? 0 : this.getEndDistance()
						.hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		result = 37 * result
				+ (getHostId() == null ? 0 : this.getHostId().hashCode());
		return result;
	}

}