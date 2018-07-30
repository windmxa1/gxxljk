package org.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.ZGxHostDao;
import org.dao.ZUserDao;
import org.dao.imp.ZGxHostDaoImp;
import org.dao.imp.ZUserDaoImp;
import org.model.ZGxHost;
import org.model.ZUser;
import org.start.Demo_ver_7;
import org.start.MyThread;
import org.tools.R;

import com.opensymphony.xwork2.ActionContext;
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
	private Integer sort;
	private String port;

	/**
	 * 获取所有光纤列表
	 */
	public String getGxHostList() {
		Map<String, Object> session1 = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session1.get("user");

		ZGxHostDao gDao = new ZGxHostDaoImp();
		Long count;
		List<ZGxHost> list;
		if (isCentral(user.getId())) {
			list = gDao.getAllList(start, limit);
			count = gDao.getCount();
		} else {
			list = gDao.getAllList(start, limit, user.getId());
			count = gDao.getCount(user.getId());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		map.put("list", list);
		result = R.getJson(1, "", map);
		return SUCCESS;
	}

	/**
	 * 判断是否为总局工作人员
	 * 
	 * @param userid
	 */
	private boolean isCentral(Long userid) {
		ZUserDao uDao = new ZUserDaoImp();
		if (uDao.getUserBelong(userid).contains("总局")) {
			// System.out.println("总局人员");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 添加光纤设备
	 */
	public String addGxHost() {
		ZGxHost gxHost = new ZGxHost(host, name, belong, status, port, sort);
		if (description != null) {
			gxHost.setDescription(description);
		}
		ZGxHostDao gDao = new ZGxHostDaoImp();
		if (gDao.addGxHost(gxHost) > 0) {
			StringBuilder str = new StringBuilder();
			if (port.equals("2009")) {
				str.append("sh /home/socketControl/socketSafe.sh " + host + " "
						+ port);
			} else {
				str.append("sh /home/socketControl/socketGxxljk.sh " + host
						+ " " + port);
			}
			try {
				Runtime.getRuntime().exec(str.toString());
				str.setLength(0);
				result = R.getJson(1, "正在连接，请稍后片刻刷新列表。。。", "");
			} catch (IOException e) {
				result = R.getJson(0, host + "连接失败，请手动重连", "");
			}
		} else {
			result = R.getJson(0, "添加失败，请重试。。", "");
		}
		return SUCCESS;
	}

	/**
	 * 修改光纤列表
	 */
	public String updateGxHost() {
		ZGxHost gxHost = new ZGxHost(id, name, description, belong, sort);
		ZGxHostDao gDao = new ZGxHostDaoImp();
		if (gDao.update(gxHost)) {
			result = R.getJson(1, "修改成功", "");
		} else {
			result = R.getJson(0, "修改失败", "");
		}
		return SUCCESS;
	}

	/**
	 * 删除光纤设备
	 */
	public String delGxHost() {
		ZGxHostDao gDao = new ZGxHostDaoImp();
		String[] cmd1 = null;
		String[] cmd2 = null;
		if (gDao.delGxHost(id)) {
			StringBuilder str = new StringBuilder();
			if (port.equals("2009")) {
				cmd1 = new String[] {
						"/bin/sh",
						"-c",
						" kill -9 `ps -ef | grep 'socketSafe.sh " + host
								+ " ' | awk '{print $2}'`" };
				cmd2 = new String[] {
						"/bin/sh",
						"-c",
						" kill -9 `ps -ef | grep 'socketSafe.jar " + host
								+ " ' | awk '{print $2}'`" };
			} else {
				cmd1 = new String[] {
						"/bin/sh",
						"-c",
						" kill -9 `ps -ef | grep 'socketGxxljk.sh " + host
								+ " ' | awk '{print $2}'`" };
				cmd2 = new String[] {
						"/bin/sh",
						"-c",
						" kill -9 `ps -ef | grep 'socketGxxljk.jar " + host
								+ " ' | awk '{print $2}'`" };
			}
			try {
				Runtime.getRuntime().exec(cmd1);
				Thread.sleep(1000);
				Runtime.getRuntime().exec(cmd2);
				str.setLength(0);
				result = R.getJson(1, "删除成功", "");
			} catch (Exception e) {
				result = R.getJson(0, "删除失败", "");
			}
		} else {
			result = R.getJson(0, "删除失败", "");
		}
		return SUCCESS;
	}

	/**
	 * 激活光纤设备
	 */
	public String activeGxHost() {
		result = R.getJson(1, "当前版本不需要手动激活", "");
		return SUCCESS;
	}

	/**
	 * 获取所有分局信息
	 * 
	 * @return
	 */
	public String getBelongList() {
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
