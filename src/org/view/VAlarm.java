package org.view;

/**
 * VAlarm entity. @author MyEclipse Persistence Tools
 */

public class VAlarm implements java.io.Serializable {

	// Fields

	private VAlarmId id;

	// Constructors

	/** default constructor */
	public VAlarm() {
	}

	/** full constructor */
	public VAlarm(VAlarmId id) {
		this.id = id;
	}

	// Property accessors

	public VAlarmId getId() {
		return this.id;
	}

	public void setId(VAlarmId id) {
		this.id = id;
	}

}