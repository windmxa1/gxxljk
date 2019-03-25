package org.model;

/**
 * ZWebsocketCtl entity. @author MyEclipse Persistence Tools
 */

public class ZWebsocketCtl implements java.io.Serializable {

	// Fields

	private Long id;
	private String host;
	private Long userid;

	// Constructors

	/** default constructor */
	public ZWebsocketCtl() {
	}

	/** full constructor */
	public ZWebsocketCtl(String host, Long userid) {
		this.host = host;
		this.userid = userid;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

}