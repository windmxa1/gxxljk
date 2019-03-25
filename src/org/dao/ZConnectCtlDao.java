package org.dao;

import org.model.ZConnectCtl;


public interface ZConnectCtlDao {
	/**
	 * 建立连接，如果有记录则更新，否则增加一条连接记录
	 */
	public Boolean saveOrUpdate(Long userid,Integer type,String threadId);
	/**
	 * 查询连接
	 * @param type 0为报警，1为异常
	 */
	public ZConnectCtl getConnect(Long userid,Integer type);
	/**
	 * 获取当前连接的线程Id 
	 * @param type 0为报警，1为异常
	 */
	public String getThreadId(Long userid,Integer type);
	
	/**
	 * 在服务器启动的时候删除所有长轮询连接记录
	 */
	public boolean deleteAll();
}
