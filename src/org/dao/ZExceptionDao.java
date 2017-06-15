package org.dao;

import java.util.List;
import java.util.Set;

import org.model.ZException;
import org.view.VExceptionId;

public interface ZExceptionDao {
	/**
	 * 1.添加连接异常信息
	 * 
	 * @param a
	 * @return
	 */
	public boolean addException(ZException a);
	/**
	 * 2.确认连接异常，将ack字段设置为1，表示已确认
	 * 
	 * @param id
	 * @return
	 */
	public boolean updateAck(long id);
	
	/**
	 * 2.1一键确认全部
	 * @return
	 */
	public boolean updateAllAck();
	
	/**
	 * 3.获取所有连接异常列表
	 * 
	 * @param type
	 *            类型，根据该参数返回已确认的以及所有的连接异常列表
	 * @return type=1,返回确认，type=1，返回已确认的，type=2，返回所有的
	 */
	public List getExceptionList(Integer start, Integer limit, Integer type,Long userid);
	public List getExceptionList(Integer start, Integer limit, Integer type);
	/**
	 * 4.获取总数
	 * @param type 0返回未确认，1返回已确认，2返回所有
	 * @return
	 */
	public long getCount(Integer type,Long userid);
	public long getCount(Integer type);
	/**
	 * 5.获取未确认连接异常的id数组
	 */
	public Set<Long> getUnACKExceptionIds(Long userid); 
	public Set<Long> getUnACKExceptionIds(); 
	/**
	 * 6.获取所有未确认的连接异常列表
	 */
	public List getUnACKExceptionList(Long userid);
	public List getUnACKExceptionList();
	/**
	 * 7.获取指定时间段的数据
	 */
	public List<VExceptionId> getExceptionList(Integer start, Integer limit, Integer type,
			String start_time, String end_time,Long userid);
	public List<VExceptionId> getExceptionList(Integer start, Integer limit, Integer type,
			String start_time, String end_time);
	/**
	 * 8.获取指定时间段的数据的数目
	 */
	public Long getExceptionCount(Integer type, String start_time, String end_time,Long userid);
	public Long getExceptionCount(Integer type, String start_time, String end_time);
	/**
	 * 9.删除指定时间段的数据
	 */
	public boolean deleteException(Long start_clock, Long end_clock,Long userid);
	public boolean deleteException(Long start_clock, Long end_clock);
}
