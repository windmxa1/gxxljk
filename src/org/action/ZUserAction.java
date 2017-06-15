package org.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.dao.ZAuthorityDao;
import org.dao.ZRoleDao;
import org.dao.ZUserDao;
import org.dao.imp.ZAuthorityDaoImp;
import org.dao.imp.ZRoleDaoImp;
import org.dao.imp.ZUserDaoImp;
import org.model.ZUser;
import org.tools.R;
import org.view.VRaId;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ZUserAction extends ActionSupport {
	private Long id;
	private String username;
	private String password;
	private String belong;	//register时传过来的所属分局
	private String role;	//注册时传过来的角色
	
	private String oldpwd;	//修改密码时传输，旧密码

	private Object result;

	public String login() {
		ZUserDao uDao = new ZUserDaoImp();
		ZRoleDao rDao = new ZRoleDaoImp();
		ZAuthorityDao aDao = new ZAuthorityDaoImp();
		ZUser u = uDao.getUser(username, password);

		if (u != null) {
			Map<String, Object> session = ActionContext.getContext()
					.getSession();
			session.put("user", u);
			
			long rid = rDao.getRByU(u.getId());
			List<VRaId> list = aDao.getRAList(rid);
			session.put("raList", list);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", u.getId());
			result = R.getJson(1, "登录成功", map);
		} else {
			result = R.getJson(0, "用户名或密码错误", false);
		}
		return SUCCESS;
	}

	public String register() throws Exception {
		ZUserDao uDao = new ZUserDaoImp();
		ZRoleDao rDao = new ZRoleDaoImp();
		if (uDao.getUser(username) == null) {
			long uid=uDao.addUser(username, password);
			if (uid!=-1){
				System.out.println("role:"+role);
				System.out.println("belong:"+belong);
				uDao.addUB(uid, belong);
				rDao.addUR(uid, Long.parseLong(role));
//				if(role.equals(3))
//					rDao.addUR(uid, 3);	//普通用户
//				else if(role.equals(2))
//					rDao.addUR(uid, 2);	//管理员
				result = R.getJson(1, "注册成功", true);
			}else
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
		session1.invalidate();
//		session1.removeAttribute("user");
//		session1.removeAttribute("start_time");
//		session1.removeAttribute("end_time");
//		session1.removeAttribute("UnACKException");
//		session1.removeAttribute("UnACKAlarm");
		result = R.getJson(1, "", "");
		return SUCCESS;
	}
	
	public String deleteUser()throws Exception{
		ZUserDao uDao = new ZUserDaoImp();
		ZRoleDao rDao = new ZRoleDaoImp();
		if(uDao.deleteUser(id)){
			rDao.deleteUR(id);
			uDao.deleteUB(id);
			result=R.getJson(1, "删除用户成功", true);
//			if(rDao.deleteUR(id))
//				result=R.getJson(1, "删除用户成功", true);
//			else
//				result=R.getJson(-1, "删除用户成功，删除用户角色关联失败", false);
		}else {
			result=R.getJson(0, "删除用户失败", false);
		}
		return SUCCESS;
	}
	
	public String updateUser()throws Exception{
		ZUserDao uDao = new ZUserDaoImp();
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		if(uDao.getUser(user.getUsername(), oldpwd)==null){
			result=R.getJson(-1, "密码错误", false);
		}else if(uDao.updateUser(user.getId(), password))
			result=R.getJson(1, "修改密码成功", true);
		else
			result=R.getJson(0, "修改密码失败", false);
		return SUCCESS;
	}
	
	public String getSession()throws Exception{
		Map<String, Object> session = ActionContext.getContext().getSession();
		ZUser user = (ZUser) session.get("user");
		user.setPassword("******");
		result=R.getJson(1, "session", user);
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

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOldpwd() {
		return oldpwd;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}
	
}
