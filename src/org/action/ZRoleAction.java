package org.action;

import java.util.List;

import org.dao.imp.ZRoleDaoImp;

import org.dao.ZRoleDao;
import org.model.ZRole;
import org.tools.R;
import org.view.VUrId;

import com.opensymphony.xwork2.ActionSupport;

public class ZRoleAction extends ActionSupport{
	private Object result;
	
	
	public String getRoleList()throws Exception{
		ZRoleDao rDao = new ZRoleDaoImp();
		List<ZRole> list = rDao.getRoleList();
		result = R.getJson(1, "", list);
		
		return SUCCESS;
	}
	
	public String getURList()throws Exception{
		ZRoleDao rDao = new ZRoleDaoImp();
		List<VUrId> urList = rDao.getURList();
		result = R.getJson(1, "", urList);
		return SUCCESS;
	}
	
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
}
