package org.model;

public class AlarmBean {
	private String command_code;
	private String description;
	private String seq_num;
	private String alarm_id;
	private String alarm_level;
	private String distance;
	private String occur_time;
	private String last_time;
	
	public AlarmBean(String command_code, String description, String seq_num,
			String alarm_id, String alarm_level, String distance,
			String occur_time, String last_time) {
		super();
		this.command_code = command_code;
		this.description = description;
		this.seq_num = seq_num;
		this.alarm_id = alarm_id;
		this.alarm_level = alarm_level;
		this.distance = distance;
		this.occur_time = occur_time;
		this.last_time = last_time;
	}
	public AlarmBean(){
		
	}
	
	public String getCommand_code() {
		return command_code;
	}
	public void setCommand_code(String command_code) {
		this.command_code = command_code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSeq_num() {
		return seq_num;
	}
	public void setSeq_num(String seq_num) {
		this.seq_num = seq_num;
	}
	public String getAlarm_id() {
		return alarm_id;
	}
	public void setAlarm_id(String alarm_id) {
		this.alarm_id = alarm_id;
	}
	public String getAlarm_level() {
		return alarm_level;
	}
	public void setAlarm_level(String alarm_level) {
		this.alarm_level = alarm_level;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getOccur_time() {
		return occur_time;
	}
	public void setOccur_time(String occur_time) {
		this.occur_time = occur_time;
	}
	public String getLast_time() {
		return last_time;
	}
	public void setLast_time(String last_time) {
		this.last_time = last_time;
	}
	
}
