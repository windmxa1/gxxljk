package org.start;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dao.ZConnectCtlDao;
import org.dao.ZGxHostDao;
import org.dao.ZWebsocketCtlDao;
import org.dao.imp.ZConnectCtlDaoImp;
import org.dao.imp.ZGxHostDaoImp;
import org.dao.imp.ZWebsocketCtlDaoImp;
import org.model.ZGxHost;
import org.model.ZWebsocketCtl;

public class ListenerForConnect implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 服务器启动时，自动重置所有连接
		// 长轮询的线程连接数重置
		ZConnectCtlDao cDao = new ZConnectCtlDaoImp();
		cDao.deleteAll();
		// 光纤状态重置
		ZGxHostDao gDao = new ZGxHostDaoImp();
		gDao.resetState();
		// 光纤线程自动启动
		List<ZGxHost> list = gDao.getAllList(0, -1);
		for (ZGxHost gx : list) {
//			if (!gx.getHost().equals("10.93.5.111")) {
				MyThread thread = new MyThread(gx.getHost(), gx.getPort());
				thread.start();
//			}
		}
		// 光纤websocket重置
		ZWebsocketCtlDao wDao = new ZWebsocketCtlDaoImp();
		wDao.deleteAll();
	}

}
