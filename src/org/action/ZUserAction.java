package org.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.dao.ZUserDao;
import org.dao.imp.ZUserDaoImp;
import org.model.ZUser;
import org.tools.R;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ZUserAction extends ActionSupport {
	private Long id;
	private String username;
	private String password;

	private Object result;

	public String login() {
		ZUserDao uDao = new ZUserDaoImp();
		ZUser u = uDao.getUser(username, password);

		if (u != null) {
			Map<String, Object> session = ActionContext.getContext()
					.getSession();
			session.put("user", u);

			result = R.getJson(1, "登录成功", true);
		} else {
			result = R.getJson(0, "用户名或密码错误", false);
		}
		return SUCCESS;
	}

	public String register() throws Exception {
		ZUserDao uDao = new ZUserDaoImp();
		if (uDao.getUser(username) == null) {
			if (uDao.addUser(username, password))
				result = R.getJson(1, "注册成功", true);
			else
				result = R.getJson(0, "注册失败", false);
		} else {
			result = R.getJson(0, "用户名已使用", false);
		}
		return SUCCESS;
	}

	public String logout() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		session.clear();
		session1.removeAttribute("user");
		session1.removeAttribute("start_time");
		session1.removeAttribute("end_time");
		session1.removeAttribute("UnACKException");
		session1.removeAttribute("UnACKAlarm");
		result = R.getJson(1, "", "");
		return SUCCESS;
	}

	// ================================================================
	public Long getId() {
		return id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
