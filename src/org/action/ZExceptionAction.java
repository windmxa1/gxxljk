package org.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.ZAlarmDao;
import org.dao.ZExceptionDao;
import org.dao.imp.ZAlarmDaoImp;
import org.dao.imp.ZExceptionDaoImp;
import org.model.ZUser;
import org.tools.R;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ZExceptionAction extends ActionSupport {
	private Long id;
	private Long time;
	private Integer ack;

	private Integer start;
	private Integer limit;

	private Object result;

	public String ackException() throws Exception {
		ZExceptionDao eDao = new ZExceptionDaoImp();
		if (eDao.updateAck(id))
			result = R.getJson(1, "确认连接异常成功", true);
		else
			result = R.getJson(0, "确认连接异常失败", false);
		return SUCCESS;
	}

	public String getExceptionList() throws Exception {
		ZExceptionDao eDao = new ZExceptionDaoImp();
		Map<String, Object> map = new HashMap<>();
		long count = eDao.getCount();
		List list = eDao.getExceptionList(start, limit, 2);
		map.put("count", count);
		map.put("list", list);
		result = R.getJson(1, "获取异常列表成功", map);
		return SUCCESS;
	}
	
	/**
	 * 检测是否有最新的线路异常信息
	 */
	public String checkException(){
		ZExceptionDao eDao = new ZExceptionDaoImp();
		
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
				if (eDao.getUnACKExceptionCount()> 0L) {
					result = R.getJson(0,  "请注意，线路连接异常，请及时排查","");
					break;
				}
				Thread.sleep(1000 * 1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			result = R.getJson(0, "线程出错，需重新发送请求","");
		}
		return SUCCESS;
	}
	
	public String getLatestException(){
		ZExceptionDao eDao = new ZExceptionDaoImp();
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		if (user == null) {
			result = R.getJson(0, "检测到您还没有进行登录，请重新登录","");
			return SUCCESS;
		}
		List list = eDao.getUnACKExceptionList();
		result = R.getJson(1, "", list);
		return SUCCESS;
	}
	
	
	// =================================================================
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
