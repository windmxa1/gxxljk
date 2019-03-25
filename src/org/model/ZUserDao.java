package org.model;

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
	/**
	 * 3.检验用户名
	 * @param username
	 * @return
	 */
	public ZUser getUser(String username);
	/**
	 * 4 删除用户
	 * @param id
	 * @return
	 */
	public boolean deleteUser(long id);
	/**
	 * 5 修改密码
	 * @param password
	 * @return
	 */
	public boolean updateUser(long id,String password);
}
