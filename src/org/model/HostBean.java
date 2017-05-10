package org.model;

public class HostBean {
	private String host;
	private String port;
	private Long userid;

	public HostBean(String host, String port, Long userid) {
		this.host = host;
		this.port = port;
		this.userid = userid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public HostBean() {
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
}
