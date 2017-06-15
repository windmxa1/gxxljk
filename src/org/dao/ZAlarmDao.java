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
	 * 2.1一键确认全部
	 * @return
	 */
	public boolean updateAllAck();
	
	/**
	 * 3.获取所有报警列表
	 * 
	 * @param type
	 *            类型，根据该参数返回已确认的以及所有的告警列表
	 * @return type=1,返回确认，type=1，返回已确认的，type=2，返回所有的
	 */
	public List getAlarmList(Integer start, Integer limit, Integer type,
			Long userid);

	public List getAlarmList(Integer start, Integer limit, Integer type);

	/**
	 * 4.获取总数
	 * 
	 * @param type
	 *            0返回未确认，1返回已确认，2返回所有
	 * @return
	 */
	public long getCount(Integer type, Long userid);

	public long getCount(Integer type);

	/**
	 * 5.获取未确认报警的id数组
	 */
	public List<Long> getUnACKAlarmIds(Long userid);

	public List<Long> getUnACKAlarmIds();

	// /**
	// * 6.1.获取未确认报警的井位地址
	// */
//	public Set<String> getUnACKWell(Long userid);
//
//	public Set<String> getUnACKWell();
	/**
	 * 6.获取未确认报警的井位经纬度 
	 */
	public Set<String> getUnACKWellLatLon(Long userid);
	
	public Set<String> getUnACKWellLatLon();

	/**
	 * 7.获取所有未确认的告警列表
	 */
	public List<VAlarmId> getUnACKAlarmList(Long userid);

	public List<VAlarmId> getUnACKAlarmList();

	/**
	 * 8.获取指定时间段的数据
	 */
	public List<VAlarmId> getAlarmList(Integer start, Integer limit,
			Integer type, String start_time, String end_time, Long userid);

	public List<VAlarmId> getAlarmList(Integer start, Integer limit,
			Integer type, String start_time, String end_time);

	/**
	 * 9.获取指定时间段的数据的数目
	 */
	public Long getAlarmCount(Integer type, String start_time, String end_time,
			Long userid);

	public Long getAlarmCount(Integer type, String start_time, String end_time);

	/**
	 * 10.删除指定时间段的数据
	 */
	public boolean deleteAlarm(Long start_clock, Long end_clock, Long userid);

	public boolean deleteAlarm(Long start_clock, Long end_clock);
}
