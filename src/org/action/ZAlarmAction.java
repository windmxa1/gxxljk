package org.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.ZAlarmDao;
import org.dao.imp.ZAlarmDaoImp;
import org.model.ZUser;
import org.tools.R;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ZAlarmAction extends ActionSupport {
	private Long id;
	private Long time;
	private String distance;
	private String description;
	private String level;
	private Integer ack;

	private Integer start;
	private Integer limit;

	private Object result;

	public String ackAlarm() throws Exception {
		ZAlarmDao aDao = new ZAlarmDaoImp();
		if (aDao.updateAck(id))
			result = R.getJson(1, "确认报警成功", true);
		else
			result = R.getJson(0, "确认报警失败", false);
		return SUCCESS;
	}
	/**
	 * 获取报警列表
	 * @return
	 * @throws Exception
	 */
	public String getAlarmList() throws Exception {
		ZAlarmDao aDao = new ZAlarmDaoImp();
		Map<String, Object> map = new HashMap<>();
		
		long count = aDao.getCount();
		List list = aDao.getAlarmList(start, limit,2);
		map.put("total", count);
		map.put("list", list);
		result = R.getJson(1, "", map);
		return SUCCESS;
	}
	/**
	 * 检测是否有最新的报警信息
	 */
	public String checkAlarm(){
		ZAlarmDao aDao = new ZAlarmDaoImp();
		
		long start_time = System.currentTimeMillis() / 1000;
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		if (user == null) {
			result = R.getJson(0, "检测到您还没有进行登录，请重新登录", "");
			return SUCCESS;
		}
		try {
			while (true) {
				if ((System.currentTimeMillis() / 1000) - start_time > 5 * 60) {
					result = R.getJson(0, "连接超时，默认超时时间为5分钟", "");
					break;
				}
				if (aDao.getUnACKAlarmCount() > 0L) {
					result = R.getJson(0,"请注意，线路安全告警!","");
					break;
				}
				Thread.sleep(1000 * 1);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = R.getJson(0, "线程出错，需重新发送请求","");
		}
		return SUCCESS;
	}
	/**
	 * 获取最新报警信息
	 */
	public String getLatestAlarm(){
		ZAlarmDao aDao = new ZAlarmDaoImp();
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		if (user == null) {
			result = R.getJson(0, "检测到您还没有进行登录，请重新登录","");
			return SUCCESS;
		}
		List list = aDao.getUnACKAlarmList();
		result = R.getJson(1, "", list);
		return SUCCESS;
	}
	
	
	
	// ===============================================================
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

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getAck() {
		return ack;
	}

	public void setAck(Integer ack) {
		this.ack = ack;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
