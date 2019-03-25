package org.dao;

import java.util.List;

public interface ZAuthorityDao {
	/**
	 * 1 获取权限列表
	 * @return
	 */
	public List getAuthorityList();
	
	/**
	 * 
	 * @param rid
	 * @return
	 */
	public List getRAList(long rid);
//	public boolean addRA(int roleId,int );
}
