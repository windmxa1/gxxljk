package org.dao;

import java.util.List;

import org.model.ZGxHost;

public interface ZGxHostDao {
	/**
	 * 获取所有光纤主机列表
	 */
	public List<ZGxHost> getAllList(Integer start , Integer limit);
	/**
	 * 获取光纤主机的总数目
	 */
	public Long getCount();
	/**
	 * 修改光纤状态为激活状态,1
	 */
	public Boolean updateOnStatus(String host);
	/**
	 * 修改光纤状态为未激活状态，0
	 */
	public Boolean updateOffStatus(String host);
	/**
	 * 增加新的光纤
	 */
	public Long addGxHost(ZGxHost host);
	/**
	 * 删除光纤
	 */
	public Boolean delGxHost(Long id);
	/**
	 * 获取光纤对象
	 */
	public ZGxHost getGxHost(String host);
	/**
	 * 获取分局列表
	 * @return
	 */
	public List getBelongList();
}
