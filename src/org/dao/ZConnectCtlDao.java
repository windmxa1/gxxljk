package org.dao;

import org.model.ZConnectCtl;


public interface ZConnectCtlDao {
	/**
	 * 建立连接，如果有记录则更新，否则增加一条连接记录
	 */
	public Long insertConnect(ZConnectCtl connectCtl);
	/**
	 * 查询连接
	 * @param type 0为报警，1为异常
	 */
	public ZConnectCtl getConnect(Long userid,Integer type);
	/**
	 * 获取连接总数
	 * @param type 0为报警，1为异常
	 */
	public Integer getConnectCount(Long userid,Integer type);
	/**
	 * 在服务器启动的时候将全部连接数都重置为0
	 */
	public boolean resetConnectCount();
}
