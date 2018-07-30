package org.start;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.dao.ZAlarmDao;
import org.dao.ZExceptionDao;
import org.dao.ZGxHostDao;
import org.dao.imp.ZAlarmDaoImp;
import org.dao.imp.ZExceptionDaoImp;
import org.dao.imp.ZGxHostDaoImp;
import org.model.AlarmBean;
import org.model.ZAlarm;
import org.model.ZException;
import org.tools.AlarmUtil;
import org.tools.JsonUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Demo_ver_6 extends Thread {
	private String host;
	private String port;
	private Socket socket = null;

	public Demo_ver_6(String host, String port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() {
		System.out.println("thread start running");
		Integer ErrorCount = 0;
		connect(host, port, ErrorCount);
	}

	public static void main(String[] args) {
		Demo_ver_6 demo_ver_6 = new Demo_ver_6("192.168.43.244", "6179");
		demo_ver_6.start();
	}

	public Object connect(String host, String port, Integer ErrorCount) {
		DataOutputStream out = null;
		// ZGxHostDao gDao = new ZGxHostDaoImp();
		int errorCase = 0;
		DataInputStream dis = null;
		try {
			Thread.sleep(3 * 1000);// 设置延时3秒，即断开重连的缓冲时间
			if (socket == null) {
				socket = new Socket(host, Integer.parseInt(port));
			}
			socket.setSoTimeout(30 * 1000);
			out = new DataOutputStream(socket.getOutputStream());
			String json = "{\"command_code\":\"8000001\",\"description\":\"login\",\"seq_num\":\"0\",\"account\":\"ffrc\",\"password\":\"ffrc12345\"}";
			// System.out.println("客户端发送数据：" + json);
			out.writeInt(json.getBytes().length);
			// 在WINDOWS上不能转换成UTF-8，会导致发送中文数据时数据长度不对，程序崩溃
			out.write(json.getBytes("UTF-8"));
			// out.write(json.getBytes());
			out.flush();
			// 更新为激活状态
			// gDao.updateOnStatus(host);

			dis = new DataInputStream(socket.getInputStream());

			byte b[] = null;
			while (true) {
				String result = "";
				System.out.println(host + "MyThread running...");
				int len = dis.readInt();
				ErrorCount = 0;
				while (dis.read() != -1) {
					System.out.println("s1");
				}
				System.out.println("s2");
				if (result.contains("\"command_code\":\"5000001\"")) {// 检测报警
					// System.out.println("接收到报警");
					// DB操作
					ZAlarmDao aDao = new ZAlarmDaoImp();
					ObjectMapper mapper = JsonUtils.getInstance();
					AlarmBean ab = mapper.readValue(result, AlarmBean.class);
					try {
						long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.parse(ab.getOccur_time()).getTime() / 1000;

						ZAlarm a = new ZAlarm(time, ab.getDistance(),
								AlarmUtil.getDescription(ab.getAlarm_level()),
								ab.getAlarm_level(), 0, ab.getLast_time(), host);
						aDao.addAlarm(a);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
				}
				result = "";
				Thread.sleep(150);
			}
		} catch (SocketTimeoutException e) {
			System.out.println("响应超时");
			errorCase = 0;
			// **DB操作，记录连接异常**/
			ZExceptionDao eDao = new ZExceptionDaoImp();
			ZException z = new ZException(System.currentTimeMillis() / 1000, 0,
					"光纤服务器响应超时，请重启服务器", host);
			eDao.addException(z);
			// =================================
			// gDao.updateOffStatus(host);
			try {
				if (socket != null) {
					socket.close();
					out.close();
					dis.close();
				}
				socket = null;
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				connect(host, port, ErrorCount);
			}
		} catch (ConnectException e) {
			errorCase = 1;
			ErrorCount++;
			System.out.println("光纤服务器连接异常,3秒后自动重连");
			e.printStackTrace();
			if (ErrorCount >= 3) {
				ErrorCount = 0;
				// **DB操作，记录连接异常**/
				ZExceptionDao eDao = new ZExceptionDaoImp();
				ZException z = new ZException(
						System.currentTimeMillis() / 1000, 0, "光纤服务器连接异常", host);
				eDao.addException(z);
				// =================================
				// gDao.updateOffStatus(host);
				System.out.println("error>=3");
			}
			try {
				if (socket != null) {
					socket.close();
					out.close();
					dis.close();
				}
				socket = null;
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				connect(host, port, ErrorCount);
			}
		} catch (InterruptedException e) {
			errorCase = 2;
			e.printStackTrace();
			System.out.println("中断异常");
		} catch (IOException e) {
			errorCase = 3;
			ErrorCount++;
			System.out.println("光纤服务器连接异常,3秒后自动重连");
			e.printStackTrace();
			if (ErrorCount >= 3) {
				ErrorCount = 0;
				// e.printStackTrace();
				// **DB操作，记录连接异常**/
				ZExceptionDao eDao = new ZExceptionDaoImp();
				ZException z = new ZException(
						System.currentTimeMillis() / 1000, 0,
						"光纤服务器异常，请确认服务器Ip以及端口是否改变", host);
				eDao.addException(z);
				// =================================
				System.out.println("error>=3");
				// gDao.updateOffStatus(host);
			}
			try {
				if (socket != null) {
					socket.close();
					out.close();
					dis.close();
				}
				socket = null;
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				connect(host, port, ErrorCount);
			}
		} finally {
			System.out.println(host + " error:" + errorCase);
			try {
				if (socket != null) {
					socket.close();
					out.close();
					dis.close();
				}
				socket = null;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

}