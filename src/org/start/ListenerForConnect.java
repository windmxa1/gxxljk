package org.start;

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
import org.hibernate.connection.ProxoolConnectionProvider;
import org.logicalcobwebs.proxool.HouseKeeperController;
import org.logicalcobwebs.proxool.HouseKeeperThread;
import org.logicalcobwebs.proxool.ProxoolFacade;
import org.logicalcobwebs.proxool.admin.servlet.AdminServlet;
import org.model.ZGxHost;
import org.model.ZWebsocketCtl;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.sun.org.apache.xml.internal.utils.ThreadControllerWrapper.ThreadController;

public class ListenerForConnect implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
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
		// List<ZGxHost> list = gDao.getAllList(0, -1);
		// while (true) {
		// for (ZGxHost gx : list) {
		// // try {
		// // Runtime.getRuntime().exec(
		// // "sh /home/socketControl/socketStart.sh " + gx.getHost()
		// // + " " + gx.getPort());
		// // } catch (Exception e) {
		// // e.printStackTrace();
		// // }
		// Demo_ver_7 thread = new Demo_ver_7(gx.getHost(), gx.getPort());
		// thread.start();
		// try {
		// thread.join();
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		// }
	}
}
