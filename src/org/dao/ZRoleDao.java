package org.dao;

import java.util.List;

import org.hibernate.sql.Delete;
import org.model.ZRole;
import org.view.VUrId;

public interface ZRoleDao {
	/**
	 * 1创建角色
	 * @param role
	 * @return
	 */
	public boolean addRole(String role);
	
	/**
	 * 2检验角色名是否可用
	 * @param role
	 * @return
	 */
	public ZRole getRole(String role);
	
	/**
	 * 3 绑定用户-角色
	 * @param uid
	 * @param rid
	 * @return
	 */
	public boolean addUR(long uid,long rid);
	
	/**
	 * 4 删除用户角色关联关系，通过用户id删除
	 * @param uid
	 */
	public boolean deleteUR(long uid);
	
	/**
	 * 5 获取全部角色列表
	 * @return
	 */
	public List<ZRole> getRoleList();
	
	/**
	 * 6 通过用户id获取对应角色的对应表
	 * @param uid
	 * @return
	 */
	public List<VUrId> getURList();
	
	/**
	 * 7 通过用户id获取roel对象
	 * @param uid
	 * @return
	 */
	public long getRByU(long uid);
	
//	public boolean updateUR();	//修改关系？
}
