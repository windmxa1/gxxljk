package org.filter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.dao.ZLogDao;
import org.dao.imp.ZLogDaoImp;
import org.model.ZLog;
import org.model.ZUser;
import org.tools.R;
import org.view.VRaId;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LogInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 获取接口名
		String actionName = invocation.getInvocationContext().getName();

		HttpServletRequest request = ServletActionContext.getRequest();
		String pName = request.getContextPath(); // 获取项目名

		Map<String, Object> session = ActionContext.getContext().getSession();

		System.out.println(pName
				+ "--"
				+ new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss")
						.format(new Date()) + "--" + actionName);

		ZLogDao lDao = new ZLogDaoImp();

		if (actionName.equals("getLogList")) {// 不记录日志，但需要权限控制
			// -----权限------
			ZUser u = (ZUser) request.getSession().getAttribute("user");
			if (u != null) {
				List<VRaId> list = (List<VRaId>) session.get("raList");
//				System.out.println(list.size());
				for (VRaId vv : list) {
					if (actionName.equals(vv.getAction())) {
						return invocation.invoke();
					}
				}
				ActionContext.getContext().put("result",
						R.getJson(-900, "操作无权限", false));
				return Action.ERROR;
			} else {
				ActionContext.getContext().put("result",
						R.getJson(-999, "未登录", false));

				return Action.ERROR;
			}

		} else if (actionName.equals("login")) {
			// 要记录日志，但不需要权限
			String username = request.getParameter("username");
			ZLog log = new ZLog(username, actionName,
					new Date().getTime() / 1000);
			lDao.addLog(log);

			return invocation.invoke();
		} else if (actionName.equals("register")) {
			// 要记录日志，但需要权限
			String username = request.getParameter("username");
			ZLog log = new ZLog(username, actionName,
					new Date().getTime() / 1000);
			lDao.addLog(log);

			// -----权限------
			ZUser u = (ZUser) request.getSession().getAttribute("user");
			if (u != null) {
				List<VRaId> list = (List<VRaId>) session.get("raList");
//				System.out.println(list.size());
				for (VRaId vv : list) {
					if (actionName.equals(vv.getAction())) {
						return invocation.invoke();
					}
				}
				ActionContext.getContext().put("result",
						R.getJson(-900, "操作无权限", false));
				return Action.ERROR;
			} else {
				ActionContext.getContext().put("result",
						R.getJson(-999, "未登录", false));
				return Action.ERROR;
			}
		} else {
			ZUser u = (ZUser) request.getSession().getAttribute("user");
			if (u != null) { // 已登录
				List<VRaId> list = (List<VRaId>) session.get("raList");
//				System.out.println("权限个数："+list.size());
				for (VRaId vv : list) {
					// 有权限
					if (actionName.equals(vv.getAction())) {
						// 记日志
						if (actionName.equals("ackAlarm")
								|| actionName.equals("ackException")) {
							String dataId = request.getParameter("id");
							ZLog log = new ZLog(u.getUsername(), actionName,
									new Date().getTime() / 1000,
									Long.parseLong(dataId));
							lDao.addLog(log);
						} else {
							ZLog log = new ZLog(u.getUsername(), actionName,
									new Date().getTime() / 1000);
							lDao.addLog(log);
						}
						return invocation.invoke();
					}
				}
				// 无权限
				ActionContext.getContext().put("result",
						R.getJson(-900, "操作无权限", false));
				return Action.ERROR;
			} else { // 未登录
				ActionContext.getContext().put("result",
						R.getJson(-999, "未登录", false));

				return Action.ERROR;
			}
		}
	}
}
