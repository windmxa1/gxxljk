package org.view;

/**
 * VAlarmId entity. @author MyEclipse Persistence Tools
 */

public class VAlarmId implements java.io.Serializable {

	// Fields

	private Long id;
	private Long occurTime;
	private String distance;
	private String description;
	private String level;
	private Integer ack;
	private String lastTime;
	private String host;
	private String vtime;

	// Constructors

	/** default constructor */
	public VAlarmId() {
	}

	/** minimal constructor */
	public VAlarmId(Long id, Long occurTime, String distance,
			String description, String level, Integer ack, String lastTime,
			String host) {
		this.id = id;
		this.occurTime = occurTime;
		this.distance = distance;
		this.description = description;
		this.level = level;
		this.ack = ack;
		this.lastTime = lastTime;
		this.host = host;
	}

	/** full constructor */
	public VAlarmId(Long id, Long occurTime, String distance,
			String description, String level, Integer ack, String lastTime,
			String host, String vtime) {
		this.id = id;
		this.occurTime = occurTime;
		this.distance = distance;
		this.description = description;
		this.level = level;
		this.ack = ack;
		this.lastTime = lastTime;
		this.host = host;
		this.vtime = vtime;
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

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getVtime() {
		return this.vtime;
	}

	public void setVtime(String vtime) {
		this.vtime = vtime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VAlarmId))
			return false;
		VAlarmId castOther = (VAlarmId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getOccurTime() == castOther.getOccurTime()) || (this
						.getOccurTime() != null
						&& castOther.getOccurTime() != null && this
						.getOccurTime().equals(castOther.getOccurTime())))
				&& ((this.getDistance() == castOther.getDistance()) || (this
						.getDistance() != null
						&& castOther.getDistance() != null && this
						.getDistance().equals(castOther.getDistance())))
				&& ((this.getDescription() == castOther.getDescription()) || (this
						.getDescription() != null
						&& castOther.getDescription() != null && this
						.getDescription().equals(castOther.getDescription())))
				&& ((this.getLevel() == castOther.getLevel()) || (this
						.getLevel() != null && castOther.getLevel() != null && this
						.getLevel().equals(castOther.getLevel())))
				&& ((this.getAck() == castOther.getAck()) || (this.getAck() != null
						&& castOther.getAck() != null && this.getAck().equals(
						castOther.getAck())))
				&& ((this.getLastTime() == castOther.getLastTime()) || (this
						.getLastTime() != null
						&& castOther.getLastTime() != null && this
						.getLastTime().equals(castOther.getLastTime())))
				&& ((this.getHost() == castOther.getHost()) || (this.getHost() != null
						&& castOther.getHost() != null && this.getHost()
						.equals(castOther.getHost())))
				&& ((this.getVtime() == castOther.getVtime()) || (this
						.getVtime() != null && castOther.getVtime() != null && this
						.getVtime().equals(castOther.getVtime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getOccurTime() == null ? 0 : this.getOccurTime().hashCode());
		result = 37 * result
				+ (getDistance() == null ? 0 : this.getDistance().hashCode());
		result = 37
				* result
				+ (getDescription() == null ? 0 : this.getDescription()
						.hashCode());
		result = 37 * result
				+ (getLevel() == null ? 0 : this.getLevel().hashCode());
		result = 37 * result
				+ (getAck() == null ? 0 : this.getAck().hashCode());
		result = 37 * result
				+ (getLastTime() == null ? 0 : this.getLastTime().hashCode());
		result = 37 * result
				+ (getHost() == null ? 0 : this.getHost().hashCode());
		result = 37 * result
				+ (getVtime() == null ? 0 : this.getVtime().hashCode());
		return result;
	}

}