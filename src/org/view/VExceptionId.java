package org.view;

/**
 * VExceptionId entity. @author MyEclipse Persistence Tools
 */

public class VExceptionId implements java.io.Serializable {

	// Fields

	private Long id;
	private Long time;
	private Integer ack;
	private String description;
	private String host;
	private String vtime;

	// Constructors

	/** default constructor */
	public VExceptionId() {
	}

	/** minimal constructor */
	public VExceptionId(Long id, Long time, Integer ack, String description,
			String host) {
		this.id = id;
		this.time = time;
		this.ack = ack;
		this.description = description;
		this.host = host;
	}

	/** full constructor */
	public VExceptionId(Long id, Long time, Integer ack, String description,
			String host, String vtime) {
		this.id = id;
		this.time = time;
		this.ack = ack;
		this.description = description;
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
		if (!(other instanceof VExceptionId))
			return false;
		VExceptionId castOther = (VExceptionId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getTime() == castOther.getTime()) || (this.getTime() != null
						&& castOther.getTime() != null && this.getTime()
						.equals(castOther.getTime())))
				&& ((this.getAck() == castOther.getAck()) || (this.getAck() != null
						&& castOther.getAck() != null && this.getAck().equals(
						castOther.getAck())))
				&& ((this.getDescription() == castOther.getDescription()) || (this
						.getDescription() != null
						&& castOther.getDescription() != null && this
						.getDescription().equals(castOther.getDescription())))
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
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37 * result
				+ (getAck() == null ? 0 : this.getAck().hashCode());
		result = 37
				* result
				+ (getDescription() == null ? 0 : this.getDescription()
						.hashCode());
		result = 37 * result
				+ (getHost() == null ? 0 : this.getHost().hashCode());
		result = 37 * result
				+ (getVtime() == null ? 0 : this.getVtime().hashCode());
		return result;
	}

}