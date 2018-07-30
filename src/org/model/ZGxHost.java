package org.model;

/**
 * ZGxHost entity. @author MyEclipse Persistence Tools
 */

public class ZGxHost implements java.io.Serializable {

	// Fields

	private Long id;
	private String host;
	private String name;
	private String description;
	private String belong;
	private Integer status;
	private String port;
	private Integer sort;

	// Constructors

	/** default constructor */
	public ZGxHost() {
	}

	/** minimal constructor */
	public ZGxHost(String host, String name, String belong, Integer status,
			String port, Integer sort) {
		this.host = host;
		this.name = name;
		this.belong = belong;
		this.status = status;
		this.port = port;
		this.sort = sort;
	}

	public ZGxHost(Long id, String name, String description, String belong,
			Integer sort) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.belong = belong;
		this.sort = sort;
	}

	/** full constructor */
	public ZGxHost(String host, String name, String description, String belong,
			Integer status, String port) {
		this.host = host;
		this.name = name;
		this.description = description;
		this.belong = belong;
		this.status = status;
		this.port = port;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBelong() {
		return this.belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}