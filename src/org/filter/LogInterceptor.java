package org.filter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.dao.ZLogDao;
import org.dao.imp.ZLogDaoImp;
import org.model.ZLog;
import org.model.ZUser;
import org.tools.R;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LogInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//获取接口名
		String actionName = invocation.getInvocationContext().getName();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String pName = request.getContextPath();	//获取项目名
		
		System.out.println(pName+"--"+new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss").format(new Date())+"--"+actionName);
		
		ZLogDao lDao = new ZLogDaoImp();
		if(actionName.equals("login")||actionName.equals("register")){
			String username = request.getParameter("username");
			ZLog log = new ZLog(username, actionName, new Date().getTime()/1000);
			lDao.addLog(log);

			return invocation.invoke();
		}else{
			ZUser u =(ZUser) request.getSession().getAttribute("user");
			if(u!=null){	//已登录
				ZLog log  = new ZLog(u.getUsername(), actionName, new Date().getTime()/1000);
				lDao.addLog(log);
				return invocation.invoke();
			}else{			//未登录
				ActionContext.getContext().put("result", R.getJson(-999, "未登录", false));
				
				return Action.ERROR;
			}
		}
	}
}
