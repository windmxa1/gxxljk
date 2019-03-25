package org.model;

import java.util.List;

public class WaveBean {
	private String command_code;
	private String description;
	private String seq_num;
	private String data_id;
	private String length;
	private List<Float> data;

	public WaveBean() {

	}

	public WaveBean(String command_code, String description, String seq_num,
			String data_id, String length, List<Float> data) {
		super();
		this.command_code = command_code;
		this.description = description;
		this.seq_num = seq_num;
		this.data_id = data_id;
		this.length = length;
		this.data = data;
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

	public String getData_id() {
		return data_id;
	}

	public void setData_id(String data_id) {
		this.data_id = data_id;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public List<Float> getData() {
		return data;
	}

	public void setData(List<Float> data) {
		this.data = data;
	}


}
