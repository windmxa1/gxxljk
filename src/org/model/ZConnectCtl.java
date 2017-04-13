package org.model;

/**
 * ZConnectCtl entity. @author MyEclipse Persistence Tools
 */

public class ZConnectCtl implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private Integer count;
	private Integer type;

	// Constructors

	/** default constructor */
	public ZConnectCtl() {
	}

	/** minimal constructor 
	 * @param type 0为报警1为异常
	 * */
	public ZConnectCtl(Long userid, Integer type) {
		this.userid = userid;
		this.type = type;
	}

	/** full constructor */
	public ZConnectCtl(Long userid, Integer count, Integer type) {
		this.userid = userid;
		this.count = count;
		this.type = type;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}