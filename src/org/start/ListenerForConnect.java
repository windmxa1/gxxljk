package org.start;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dao.ZConnectCtlDao;
import org.dao.imp.ZConnectCtlDaoImp;

public class ListenerForConnect implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 服务器启动时，自动重置所有连接
		ZConnectCtlDao cDao = new ZConnectCtlDaoImp();
		cDao.resetConnectCount();
	}

}
