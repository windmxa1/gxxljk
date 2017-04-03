package org.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.ZLogDao;
import org.dao.imp.ZLogDaoImp;
import org.tools.R;

import com.opensymphony.xwork2.ActionSupport;

public class ZLogAction extends ActionSupport {
	private Long id;
	private String username;
	private String operation;
	private Long time;

	private Integer start;
	private Integer limit;

	private Object result;

	public String getLogList() throws Exception {
		ZLogDao lDao = new ZLogDaoImp();
		Map<String, Object> map = new HashMap<>();
		long count = lDao.getCount();
		List list = lDao.getLogList(start, limit);
		map.put("count", count);
		map.put("list", list);
		result = R.getJson(1, "获取日志列表", map);
		return SUCCESS;
	}

	// ====================================================
	public Long getId() {
		return id;
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

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
