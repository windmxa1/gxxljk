package org.dao;

import java.util.List;

import org.model.ZLog;

public interface ZLogDao {
	/**
	 * 1.添加日志信息
	 * @param l
	 * @return
	 */
	public boolean addLog(ZLog l);
	
	/**
	 * 2.获取日志列表
	 * @param start
	 * @param limit
	 * @return
	 */
	public List getLogList(Integer start,Integer limit);
	
	/**
	 * 3.获取总数
	 * @return
	 */
	public long getCount();
}
