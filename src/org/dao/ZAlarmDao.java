package org.dao;

import java.util.List;
import java.util.Set;

import org.model.ZAlarm;
import org.view.VAlarmId;

public interface ZAlarmDao {
	/**
	 * 1.添加报警信息
	 * 
	 * @param a
	 * @return
	 */
	public boolean addAlarm(ZAlarm a);

	/**
	 * 2.确认报警，将ack字段设置为1，表示已确认
	 * 
	 * @param id
	 * @return
	 */
	public boolean updateAck(long id);

	/**
	 * 3.获取所有报警列表
	 * 
	 * @param type
	 *            类型，根据该参数返回已确认的以及所有的告警列表
	 * @return type=1,返回确认，type=1，返回已确认的，type=2，返回所有的
	 */
	public List getAlarmList(Integer start, Integer limit, Integer type);

	/**
	 * 4.获取总数
	 * @param type 0返回未确认，1返回已确认，2返回所有
	 * @return
	 */
	public long getCount(Integer type);

	/**
	 * 5.返回未确认的报警记录数
	 */
	public long getUnACKAlarmCount();
	/**
	 * 6.获取未确认报警的id数组
	 */
	public Set<Long> getUnACKAlarmIds(); 
	
	
	/**
	 * 6.获取所有未确认的告警列表
	 */
	public List getUnACKAlarmList();

	/**
	 * 7.获取指定时间段的数据
	 */
	public List<VAlarmId> getAlarmList(Integer start, Integer limit, Integer type,
			String start_time, String end_time);

	/**
	 * 8.获取指定时间段的数据的数目
	 */
	public Long getAlarmCount(Integer type, String start_time, String end_time);
	/**
	 * 9.删除指定时间段的数据
	 */
	public boolean deleteAlarm(Long start_clock, Long end_clock);
}
