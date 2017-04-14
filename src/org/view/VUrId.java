package org.view;

/**
 * VUrId entity. @author MyEclipse Persistence Tools
 */

public class VUrId implements java.io.Serializable {

	// Fields

	private Long uid;
	private String username;
	private String password;
	private Long rid;
	private String role;

	// Constructors

	/** default constructor */
	public VUrId() {
	}

	/** full constructor */
	public VUrId(Long uid, String username, String password, Long rid,
			String role) {
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.rid = rid;
		this.role = role;
	}

	// Property accessors

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VUrId))
			return false;
		VUrId castOther = (VUrId) other;

		return ((this.getUid() == castOther.getUid()) || (this.getUid() != null
				&& castOther.getUid() != null && this.getUid().equals(
				castOther.getUid())))
				&& ((this.getUsername() == castOther.getUsername()) || (this
						.getUsername() != null
						&& castOther.getUsername() != null && this
						.getUsername().equals(castOther.getUsername())))
				&& ((this.getPassword() == castOther.getPassword()) || (this
						.getPassword() != null
						&& castOther.getPassword() != null && this
						.getPassword().equals(castOther.getPassword())))
				&& ((this.getRid() == castOther.getRid()) || (this.getRid() != null
						&& castOther.getRid() != null && this.getRid().equals(
						castOther.getRid())))
				&& ((this.getRole() == castOther.getRole()) || (this.getRole() != null
						&& castOther.getRole() != null && this.getRole()
						.equals(castOther.getRole())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUid() == null ? 0 : this.getUid().hashCode());
		result = 37 * result
				+ (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result
				+ (getPassword() == null ? 0 : this.getPassword().hashCode());
		result = 37 * result
				+ (getRid() == null ? 0 : this.getRid().hashCode());
		result = 37 * result
				+ (getRole() == null ? 0 : this.getRole().hashCode());
		return result;
	}

}