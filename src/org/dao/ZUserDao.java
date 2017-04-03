package org.dao;

import org.model.ZUser;

public interface ZUserDao {
	/**
	 * 1.登录
	 * @param username
	 * @param password
	 * @return
	 */
	public ZUser getUser(String username,String password);
	
	/**
	 * 2.注册，添加用户
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean addUser(String username,String password);
}
