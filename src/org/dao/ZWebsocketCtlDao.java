package org.dao;

import org.model.ZWebsocketCtl;

public interface ZWebsocketCtlDao {
	/**
	 * 创建或修改用户与设备的关联
	 */
	public Boolean saveOrupdate(String host,Long userid);
	/**
	 * 获取关联对象，用于检测当前连接是否已经切换，便于主动关闭不需要的线程
	 */
	public ZWebsocketCtl getWebsocketCtl(String host,Long userid);
	/**
	 * 获取关联对象
	 */
	public ZWebsocketCtl getWebsocketCtl(Long userid);
	/**
	 * 删除所有记录,服务器重启时进行
	 */
	public boolean deleteAll();
}
