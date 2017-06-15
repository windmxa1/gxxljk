package org.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.ZWellDao;
import org.dao.imp.ZWellDaoImp;
import org.model.ZUser;
import org.model.ZWell;
import org.tools.R;
import org.tools.Utils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ZWellAction extends ActionSupport {
	private Object result;
	private ZWellDao zwDao;
	private Integer start;
	private Integer limit;
	private Long id;
	private Long hostId;
	private String location;
	private Integer begin_distance, end_distance;
	private String name;
	private String lat;
	private String lon;
	private Integer range;
	private Map<String, Object> data;

	public String deleteWell() {
		zwDao = new ZWellDaoImp();
		if (zwDao.delete(id)) {
			result = R.getJson(1, "删除成功", "");
		} else {
			result = R.getJson(0, "删除失败", "");
		}
		return SUCCESS;
	}

	public String addWell() {
		zwDao = new ZWellDaoImp();
		ZWell zWell = new ZWell(name, location, lat, lon, begin_distance,
				end_distance, System.currentTimeMillis() / 1000);
		if (zwDao.saveOrUpdate(zWell) > 0) {
			result = R.getJson(1, "添加成功", "");
		} else {
			result = R.getJson(0, "添加失败", "");
		}
		return SUCCESS;
	}

	public String updateWell() {
		zwDao = new ZWellDaoImp();
		ZWell zWell = new ZWell(name, location, lat, lon, begin_distance,
				end_distance, System.currentTimeMillis() / 1000);
		zWell.setId(id);
		if (zwDao.saveOrUpdate(zWell) == 0) {
			result = R.getJson(1, "修改成功", "");
		} else {
			result = R.getJson(0, "修改失败", "");
		}
		return SUCCESS;
	}

	/**
	 * 获取所有井位列表
	 * 
	 * @return
	 */
	public String getWellList() {
		zwDao = new ZWellDaoImp();
		Map<String, Object> session1 = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session1.get("user");
		Boolean isCentral = Utils.isCentral(user.getId());
		List<ZWell> list = zwDao.getList(start, limit, isCentral, user.getId());
		if (list == null || list.size() == 0) {
			result = R.getJson(0, "", "");
		} else {
			Long count = zwDao.getCount(isCentral, user.getId());
			data = new HashMap<String, Object>();
			data.put("list", list);
			data.put("total", count);
			result = R.getJson(1, "", data);
		}
		return SUCCESS;
	}

	/**
	 * 获取光纤对应的井位列表
	 */
	public String getWellListByHostId() {
		zwDao = new ZWellDaoImp();
		Map<String, Object> session1 = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session1.get("user");
		Boolean isCentral = Utils.isCentral(user.getId());
		List<ZWell> list = zwDao.getList(start, limit, isCentral, user.getId());
		if (list == null || list.size() == 0) {
			result = R.getJson(0, "", "");
		} else {
			Long count = zwDao.getCount(isCentral, user.getId());
			data = new HashMap<String, Object>();
			data.put("list", list);
			data.put("total", count);
			result = R.getJson(1, "", data);
		}
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHostId() {
		return hostId;
	}

	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getBegin_distance() {
		return begin_distance;
	}

	public void setBegin_distance(Integer begin_distance) {
		this.begin_distance = begin_distance;
	}

	public Integer getEnd_distance() {
		return end_distance;
	}

	public void setEnd_distance(Integer end_distance) {
		this.end_distance = end_distance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
