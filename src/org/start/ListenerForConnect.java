package org.start;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dao.ZConnectCtlDao;
import org.dao.ZGxHostDao;
import org.dao.ZWebsocketCtlDao;
import org.dao.imp.ZConnectCtlDaoImp;
import org.dao.imp.ZGxHostDaoImp;
import org.dao.imp.ZWebsocketCtlDaoImp;
import org.logicalcobwebs.proxool.ProxoolFacade;
import org.model.ZGxHost;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;

public class ListenerForConnect implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		String cmd[] = { "sh /home/socketControl/closeLong.sh ",
				"sh /home/socketControl/closeLong1.sh ",
				"sh /home/socketControl/closeShort.sh ",
				"sh /home/socketControl/closeShort1.sh " };
		try {
			for (String str : cmd) {
				Runtime.getRuntime().exec(str.toString());
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("close proxool");
		// 自动关闭Proxool
		ProxoolFacade.shutdown(0);

		// HouseKeeperThread.interrupted();
		// ProxoolFacade.shutdown(0);
		try {
			System.out.println("close Driver");
			while (DriverManager.getDrivers().hasMoreElements()) {
				DriverManager.deregisterDriver(DriverManager.getDrivers()
						.nextElement());
			}
			// DriverManager.deregisterDriver(DriverManager.getDrivers()
			// .nextElement());
			System.out.println("close AbandonedThread");
			AbandonedConnectionCleanupThread.shutdown();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("init ing...");
		// 服务器启动时，自动重置所有连接
		// 长轮询的线程连接数重置
		ZConnectCtlDao cDao = new ZConnectCtlDaoImp();
		cDao.deleteAll();
		// 光纤状态重置
		ZGxHostDao gDao = new ZGxHostDaoImp();
		gDao.resetState();
		// 光纤websocket重置
		ZWebsocketCtlDao wDao = new ZWebsocketCtlDaoImp();
		wDao.deleteAll();

		// // 光纤线程自动启动
		/*
		List<ZGxHost> list = gDao.getAllList(0, -1);
		StringBuilder str = new StringBuilder();
		for (ZGxHost gx : list) {
			str.setLength(0);
			if (gx.getPort().equals("2009")) {
				str.append("sh /home/socketControl/socketSafe.sh "
						+ gx.getHost() + " " + gx.getPort());
			} else {
				str.append("sh /home/socketControl/socketGxxljk.sh "
						+ gx.getHost() + " " + gx.getPort());
			}
			try {
				Runtime.getRuntime().exec(str.toString());
			} catch (IOException e) {
				System.out.println(gx.getHost() + "连接失败");
			}
		}
		*/
	}
}
