package org.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.ZGxHostDao;
import org.dao.imp.ZGxHostDaoImp;
import org.model.ZGxHost;
import org.start.MyThread;
import org.tools.R;

import com.opensymphony.xwork2.ActionSupport;

public class ZGxHostAction extends ActionSupport {
	private Object result;
	private Integer start;
	private Integer limit;
	private Long id;
	private String host;
	private String name;
	private String description;
	private String belong;
	private Integer status;
	private String port;

	/**
	 * 获取所有光纤列表
	 */
	public String getGxHostList() {
		ZGxHostDao gDao = new ZGxHostDaoImp();
		List<ZGxHost> list = gDao.getAllList(start, limit);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", gDao.getCount());
		map.put("list", list);
		result = R.getJson(1, "", map);
		return SUCCESS;
	}

	/**
	 * 添加光纤设备
	 */
	public String addGxHost() {
		ZGxHost gxHost = new ZGxHost(host, name, belong, status, port);
		if (description != null) {
			gxHost.setDescription(description);
		}
		ZGxHostDao gDao = new ZGxHostDaoImp();
		if (gDao.addGxHost(gxHost) > 0) {
			MyThread thread = new MyThread("localhost", "6179");
			thread.start();
			result = R.getJson(1, "添加成功，正在等待激活。。", "");
		} else {
			result = R.getJson(0, "添加失败，请重试。。", "");
		}
		return SUCCESS;
	}

	/**
	 * 删除光纤设备
	 */
	public String delGxHost() {
		ZGxHostDao gDao = new ZGxHostDaoImp();
		if (gDao.delGxHost(id)) {
			result = R.getJson(1, "删除成功", "");
		} else {
			result = R.getJson(0, "删除失败", "");
		}
		return SUCCESS;
	}

	/**
	 * 激活光纤设备
	 */
	public String activeGxHost() {
		MyThread thread = new MyThread(host, port);
		thread.start();
		result = R.getJson(1, "设备激活中，请稍后", "");
		return SUCCESS;
	}
	
	/**
	 * 获取所有分局信息
	 * @return
	 */
	public String getBelongList(){
		ZGxHostDao gDao = new ZGxHostDaoImp();
		List<String> list = gDao.getBelongList();
		result = R.getJson(1, "", list);
		return SUCCESS;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
