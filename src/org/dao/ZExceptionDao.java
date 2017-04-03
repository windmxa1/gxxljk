package org.dao;

import java.util.List;

import org.model.ZException;

public interface ZExceptionDao {
	/**
	 * 1.添加设备连接异常状态信息
	 * @param s
	 * @return
	 */
	public boolean addException(ZException e);
	
	/**
	 * 2.确认设备连接异常，将ack字段设置为1，表示已确认
	 * @param id
	 * @return
	 */
	public boolean updateAck(long id);
	
	/**
	 * 3.获取所有连接异常列表
	 * @param type 类型，根据该参数返回已确认的以及所有的连接异常列表
	 * @return type=1,返回确认，type=1，返回已确认的，type=2，返回所有的
	 */
	public List getExceptionList(Integer start,Integer limit,Integer type);
	
	/**
	 * 4.获取总数
	 * @return
	 */
	public long getCount();
	/**
	 * 5.返回未确认的异常记录数
	 */
	public long getUnACKExceptionCount();
	/**
	 * 6.获取所有未确认的异常列表
	 */
	public List getUnACKExceptionList();
	
	
}
