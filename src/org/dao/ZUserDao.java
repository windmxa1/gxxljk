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
	public long addUser(String username,String password);
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
	/**
	 * 6 删除用户-所属分局的关联关系
	 * @param uid
	 */
	public boolean deleteUB(long uid);
	/**
	 * 7 添加用户-所属分局 关联关系
	 * @param uid
	 * @param belong
	 * @return
	 */
	public boolean addUB(long uid,String belong);
	/**
	 * 8 返回用户所属分局
	 */
	public String getUserBelong(Long uid); 
}
