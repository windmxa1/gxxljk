package org.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.dao.ZConnectCtlDao;
import org.dao.ZExceptionDao;
import org.dao.ZUserDao;
import org.dao.imp.ZConnectCtlDaoImp;
import org.dao.imp.ZExceptionDaoImp;
import org.dao.imp.ZUserDaoImp;
import org.model.ZConnectCtl;
import org.model.ZUser;
import org.tools.Constans;
import org.tools.PDFUtil;
import org.tools.R;
import org.view.VExceptionId;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ZExceptionAction extends ActionSupport {
	private Long id;
	private Long time;
	private Integer ack;
	private String start_time;
	private String end_time;
	private Integer start;
	private Integer limit;
	private Integer type = 2;

	private Object result;

	/**
	 * 确认异常
	 */
	public String ackException() {
		ZExceptionDao eDao = new ZExceptionDaoImp();
		if (eDao.updateAck(id))
			result = R.getJson(1, "确认连接异常成功", true);
		else
			result = R.getJson(0, "确认连接异常失败", false);
		return SUCCESS;
	}

	/**
	 * 一键确认异常
	 * @return
	 */
	public String ackAllException(){
		ZExceptionDao eDao = new ZExceptionDaoImp();
		if(eDao.updateAllAck())
			result = R.getJson(1, "一键确认异常成功", true);
		else
			result = R.getJson(0, "一键确认异常失败", false);
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
//			System.out.println("总局人员");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 2.获取异常列表
	 */
	public String getExceptionList() {
		ZExceptionDao eDao = new ZExceptionDaoImp();
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> session1 = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session1.get("user");
		try {
			HttpSession session = ServletActionContext.getRequest()
					.getSession();
			if (end_time == null || start_time == null || end_time.equals("")
					|| start_time.equals("")) {
				end_time = (String) session.getAttribute("end_time_e");
				start_time = (String) session.getAttribute("start_time_e");
			} else {
				/******* 保存缓存，确保下次查询也返回对应时间段的数据 ******/
				session.setAttribute("end_time_e", end_time);
				session.setAttribute("start_time_e", start_time);
			}
			if (start_time == null || end_time == null) {
				long count;
				List list;
				if (isCentral(user.getId())) {
					count = eDao.getCount(type);
					list = eDao.getExceptionList(start, limit, type);
				} else {
					count = eDao.getCount(type, user.getId());
					list = eDao.getExceptionList(start, limit, type,
							user.getId());
				}
				map.put("total", count);
				map.put("list", list);
				result = R.getJson(1, "", map);
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				long start_clock = sdf.parse(start_time).getTime();
				long end_clock = sdf.parse(end_time).getTime();
				if (start_clock <= end_clock) {
					// if (start_clock == end_clock) {
					Calendar calendar = new GregorianCalendar();
					calendar.setTimeInMillis(end_clock);
					calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
					end_time = sdf.format(calendar.getTime());
					// }
					List<VExceptionId> list;
					Long count;
					if (isCentral(user.getId())) {
						list = eDao.getExceptionList(start, limit, type,
								start_time, end_time);
						count = eDao.getExceptionCount(type, start_time,
								end_time);
					} else {
						list = eDao.getExceptionList(start, limit, type,
								start_time, end_time, user.getId());
						count = eDao.getExceptionCount(type, start_time,
								end_time, user.getId());
					}
					map.put("total", count);
					map.put("list", list);
					result = R.getJson(1, "", map);
				} else {
					result = R.getJson(0, "参数错误，请选择正确的日期", "");
				}
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
			result = R.getJson(0, "参数转换错误，请输入正确的日期格式yyyy-MM-dd", "");
		}
		return SUCCESS;
	}

	/**
	 * 3.检测是否有最新的线路异常信息
	 */
	public String checkException() {
		long startTime = System.currentTimeMillis() / 1000;
		ZExceptionDao eDao = new ZExceptionDaoImp();
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		if (user == null) {
			result = R.getJson(-999, "检测到您还没有进行登录，请进行登录", "");
			return SUCCESS;
		}
		ZConnectCtlDao cDao = new ZConnectCtlDaoImp();
		ZConnectCtl conCtl = cDao.getConnect(user.getId(), 1);
		if (conCtl != null) {
			conCtl.setCount(conCtl.getCount() + 1);
		} else {
			conCtl = new ZConnectCtl(user.getId(), 1);
		}
		Long id = cDao.insertConnect(conCtl);
		if (id == -1) {
			result = R.getJson(0, "建立连接失败，请重试", "");
			return SUCCESS;
		}
		try {
			int i = 0;
			while (true) {
				if ((System.currentTimeMillis() / 1000) - startTime > 5 * 60) {
					result = R.getJson(0, "连接超时，默认超时时间为5分钟", "");
					break;
				}

				Integer connectCount = cDao.getConnectCount(user.getId(), 1);
				if (i > 0 && connectCount > 1) {// 非第一次进入轮询，且连接数大于2则退出轮询，从逻辑上实现断开前一次的连接(防止重复连接)
					result = R.getJson(500, "自动断开前一次的连接，当前连接数为：" + connectCount,
							"");
					break;
				}

				Set<Long> unAckList = (Set<Long>) session1
						.getAttribute("UnACKException");
				Set<Long> unAckList2;
				if (isCentral(user.getId())) {
					unAckList2 = eDao.getUnACKExceptionIds();
				} else {
					unAckList2 = eDao.getUnACKExceptionIds(user.getId());
				}
				if (unAckList == null) {// 缓存数组为空，说明之前一个报警都没有，所以要提示报警,并且设置值
					if (unAckList2 != null && unAckList2.size() > 0) {
						session1.setAttribute("UnACKException", unAckList2);
						result = R.getJson(1, "请注意，线路连接异常，请及时排查！", "");
						break;
					}
				} else {// 如果有缓存数组,缓存数组没有全包含报警数组，说明有新报警所以要提示报警
					if (!unAckList.containsAll(unAckList2)) {
						session1.setAttribute("UnACKException", unAckList2);
						result = R.getJson(1, "请注意，线路连接异常，请及时排查！", "");
						break;
					}
				}
				Thread.sleep(1000 * 1);
				i = 1;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			result = R.getJson(0, "线程出错，需重新发送请求", "");
		} finally {
			// 结束时，连接数减1
			if (id > 0) {
				conCtl.setId(id);
			}
			conCtl.setCount(cDao.getConnectCount(user.getId(), 1) - 1);
			cDao.insertConnect(conCtl);
		}
		return SUCCESS;
	}

	/**
	 * 4.获取最新异常记录
	 */
	public String getLatestException() {
		ZExceptionDao eDao = new ZExceptionDaoImp();
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		Map<String, Object> map = new HashMap<>();
		if (user == null) {
			result = R.getJson(0, "检测到您还没有进行登录，请重新登录", "");
			return SUCCESS;
		}
		List list;
		if (isCentral(user.getId())) {
			list = eDao.getUnACKExceptionList();
		} else {
			list = eDao.getUnACKExceptionList(user.getId());
		}
		map.put("list", list);
		result = R.getJson(1, "", map);
		return SUCCESS;
	}

	/**
	 * 5.删除异常
	 */
	public String deleteException() {
		Map<String, Object> session1 = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session1.get("user");
		ZExceptionDao aDao = new ZExceptionDaoImp();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (start_time == null || end_time == null) {
			result = R.getJson(0, "缺少必要参数", "");
			return SUCCESS;
		}
		try {
			long start_clock = sdf.parse(start_time).getTime();
			long end_clock = sdf.parse(end_time).getTime();
			if (start_clock <= end_clock) {
				// if (start_clock == end_clock) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTimeInMillis(end_clock);
				calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
				// end_time = sdf.format(calendar.getTime());
				end_clock = calendar.getTimeInMillis();
			}
			// }
			if (isCentral(user.getId())) {
				if (aDao.deleteException(start_clock / 1000, end_clock / 1000)) {
					result = R.getJson(1, "删除成功", "");
				} else {
					result = R.getJson(0, "删除失败", "");
				}
			} else {
				if (aDao.deleteException(start_clock / 1000, end_clock / 1000,
						user.getId())) {
					result = R.getJson(1, "删除成功", "");
				} else {
					result = R.getJson(0, "删除失败", "");
				}
			}
		} catch (ParseException e) {
			result = R.getJson(0, "数据解析失败，请输入正确的日期格式", "");
		}
		return SUCCESS;
	}

	/**
	 * 6.导出成PDF
	 */
	public String getExceptionPDF() {
		limit = -1;
		ZExceptionDao eDao = new ZExceptionDaoImp();
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> session1 = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session1.get("user");
		try {
			if (start_time == null || end_time == null) {
				List list;
				if (isCentral(user.getId())) {
					list = eDao.getExceptionList(start, limit, type);
				} else {
					list = eDao.getExceptionList(start, limit, type,
							user.getId());
				}
				String url = PDFUtil.buidPDF(Constans.watermark, list, 1);
				map.put("url", url);
				result = R.getJson(1, "", map);
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				long start_clock = sdf.parse(start_time).getTime();
				long end_clock = sdf.parse(end_time).getTime();
				if (start_clock <= end_clock) {
					// if (start_clock == end_clock) {
					Calendar calendar = new GregorianCalendar();
					calendar.setTimeInMillis(end_clock);
					calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
					end_time = sdf.format(calendar.getTime());
					// }
					List list;
					if (isCentral(user.getId())) {

						list = eDao.getExceptionList(0, 200, type, start_time,
								end_time);
					} else {
						list = eDao.getExceptionList(0, 200, type, start_time,
								end_time, user.getId());

					}
					String url = PDFUtil.buidPDF(Constans.watermark, list, 1);
					map.put("url", url);
					result = R.getJson(1, "", map);
				} else {
					result = R.getJson(0, "参数错误，请选择正确的日期", "");
				}
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
			result = R.getJson(0, "参数转换错误，请输入正确的日期格式yyyy-MM-dd", "");
		}
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

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
