package org.model;

public class HostBean {
	private String host;
	private String port;

	public HostBean(String host, String port) {
		this.host = host;
		this.port = port;
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
