package org.view;

/**
 * VRaId entity. @author MyEclipse Persistence Tools
 */

public class VRaId implements java.io.Serializable {

	// Fields

	private Long rid;
	private String role;
	private Long aid;
	private String action;
	private String alias;

	// Constructors

	/** default constructor */
	public VRaId() {
	}

	/** full constructor */
	public VRaId(Long rid, String role, Long aid, String action, String alias) {
		this.rid = rid;
		this.role = role;
		this.aid = aid;
		this.action = action;
		this.alias = alias;
	}

	// Property accessors

	public Long getRid() {
		return this.rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getAid() {
		return this.aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VRaId))
			return false;
		VRaId castOther = (VRaId) other;

		return ((this.getRid() == castOther.getRid()) || (this.getRid() != null
				&& castOther.getRid() != null && this.getRid().equals(
				castOther.getRid())))
				&& ((this.getRole() == castOther.getRole()) || (this.getRole() != null
						&& castOther.getRole() != null && this.getRole()
						.equals(castOther.getRole())))
				&& ((this.getAid() == castOther.getAid()) || (this.getAid() != null
						&& castOther.getAid() != null && this.getAid().equals(
						castOther.getAid())))
				&& ((this.getAction() == castOther.getAction()) || (this
						.getAction() != null && castOther.getAction() != null && this
						.getAction().equals(castOther.getAction())))
				&& ((this.getAlias() == castOther.getAlias()) || (this
						.getAlias() != null && castOther.getAlias() != null && this
						.getAlias().equals(castOther.getAlias())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRid() == null ? 0 : this.getRid().hashCode());
		result = 37 * result
				+ (getRole() == null ? 0 : this.getRole().hashCode());
		result = 37 * result
				+ (getAid() == null ? 0 : this.getAid().hashCode());
		result = 37 * result
				+ (getAction() == null ? 0 : this.getAction().hashCode());
		result = 37 * result
				+ (getAlias() == null ? 0 : this.getAlias().hashCode());
		return result;
	}

}