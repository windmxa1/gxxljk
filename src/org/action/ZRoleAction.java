package org.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.imp.ZRoleDaoImp;

import org.dao.ZRoleDao;
import org.model.ZRole;
import org.tools.R;
import org.view.VUrId;

import com.opensymphony.xwork2.ActionSupport;

public class ZRoleAction extends ActionSupport{
	private Object result;
	private Integer start;
	private Integer limit;
	
	public String getRoleList()throws Exception{
		ZRoleDao rDao = new ZRoleDaoImp();
		List<ZRole> list = rDao.getRoleList();
		result = R.getJson(1, "", list);
		
		return SUCCESS;
	}
	
	public String getURList()throws Exception{
		ZRoleDao rDao = new ZRoleDaoImp();
//		List<VUrId> urList = rDao.getURList();
//		result = R.getJson(1, "", urList);
		List<VUrId> urList = rDao.getURList(start,limit);
		long count = rDao.getCount();
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("total", count);
		map.put("list", urList);
		result = R.getJson(1, "", map);
		return SUCCESS;
	}
	
	//======================================
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
}
