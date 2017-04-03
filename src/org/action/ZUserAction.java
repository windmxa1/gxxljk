package org.action;

import java.util.Map;

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

	public String login() throws Exception {
		ZUserDao uDao = new ZUserDaoImp();
		ZUser u = uDao.getUser(username, password);

		if(u!=null){
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("user", u);
			
			result=R.getJson(1, "登录成功", true);
		}else{
			result=R.getJson(0, "用户名或密码错误", false);
		}
		return SUCCESS;
	}
	public String register() throws Exception{
		ZUserDao uDao = new ZUserDaoImp();
		
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
