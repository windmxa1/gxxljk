//package org.start;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.EOFException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.ConnectException;
//import java.net.Socket;
//import java.net.SocketTimeoutException;
//import java.net.UnknownHostException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Observable;
//import java.util.Observer;
//
//import org.dao.ZAlarmDao;
//import org.dao.ZExceptionDao;
//import org.dao.imp.ZAlarmDaoImp;
//import org.dao.imp.ZExceptionDaoImp;
//import org.model.AlarmBean;
//import org.model.ZAlarm;
//import org.model.ZException;
//import org.tools.AlarmUtil;
//import org.tools.JsonUtils;
//import org.tools.PropertyUtils;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class Demo_ver_2 {
//	public static Socket socket;
//
//	public static void main(String[] args) {
//		connect();
//	}
//
//	public static Object connect() {
//
//		DataOutputStream out = null;
//		socket = null;
//		// B b = null;
//		SimpleObserver simpleObserver = null;
//		SimpleObservable simpleObservable = null;
//		try {
//			Thread.sleep(3 * 1000);// 设置延时3秒，即断开重连的缓冲时间
//			String host = PropertyUtils.getStringByKey("host");
//			String port = PropertyUtils.getStringByKey("port");
//			socket = new Socket(host, Integer.parseInt(port));
//			// socket = new Socket("localhost", 6179);
//
//			out = new DataOutputStream(socket.getOutputStream());
//			String json = "{\"command_code\":\"8000001\",\"description\":\"login\",\"seq_num\":\"0\",\"account\":\"ffrc\",\"password\":\"ffrc12345\"}";
//			// String json =
//			// "{\"command_code\":\"16001003\",\"description\":\"获取告警记录\",\"seq_num\":\"0\",\"start_date\":\"2015-01-10 01:00:00\",\"end_date\":\"2015-01-15 08:00:00\"}";
//			System.out.println("客户端发送数据：" + json);
//			out.writeInt(json.getBytes().length);
//			// 在WINDOWS上不能转换成UTF-8，会导致发送中文数据时数据长度不对，程序崩溃
//			// out.write(json.getBytes("UTF-8"));
//			out.write(json.getBytes());
//			out.flush();
//
//			// 创建观察者对象
//			simpleObserver = new SimpleObserver();
//			// 创建被观察者对象
//			simpleObservable = new SimpleObservable();
//
//			// 需要将观察者类加入到被观察者的观察者列表中
//
//			simpleObservable.addObserver(simpleObserver);
//
//			simpleObservable.setBfi(socket.getInputStream());
//			// 启动被观察者，观察者线程也会同时被启动
//			simpleObservable.thread.start();
//
//			while (true) {
//				json = "{\"command_code\":\"16000201\",\"description\":\"心跳\",\"seq_num\":\"0\"}";
//
//				// json =
//				// "{\"command_code\":\"16000201\",\"description\":\"dong dong\",\"seq_num\":\"0\"}";
//				System.out.println("客户端发送数据：" + json);
//
//				out.writeInt(json.getBytes().length);
//				// 在WINDOWS上不能转换成UTF-8，会导致发送中文数据时数据长度不对，程序崩溃
//				// out.write(json.getBytes("UTF-8"));
//				out.write(json.getBytes());
//				out.flush();
//				Thread.sleep(10 * 1000);
//			}
//		} catch (ConnectException e) {
//			// e.printStackTrace();
//			// if (simpleObservable != null) {
//			// simpleObservable.deleteObservers();
//			// }
//			System.out.println("光纤服务器连接异常,3秒后自动重连");
//			// **DB操作，记录连接异常**/
//			ZExceptionDao eDao = new ZExceptionDaoImp();
//			ZException z = new ZException(System.currentTimeMillis() / 1000, 0,
//					"光纤服务器连接异常",PropertyUtils.getStringByKey("host"));
//			eDao.addException(z);
//			// =================================
//			connect();
//
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//			System.out.println("中断异常");
//		} catch (IOException e) {
//			// e.printStackTrace();
//			System.out.println("光纤服务器连接异常,3秒后自动重连");
//			// **DB操作，记录连接异常**/
//			ZExceptionDao eDao = new ZExceptionDaoImp();
//			ZException z = new ZException(System.currentTimeMillis() / 1000, 0,
//					"光纤服务器异常，请确认服务器Ip以及端口是否改变",PropertyUtils.getStringByKey("host"));
//			eDao.addException(z);
//			// =================================
//			connect();
//		}
//		return null;
//	}
//
//}
//
//class SimpleObserver implements Observer {
//	private Socket socket;
//
//	//
//	public SimpleObserver() {
//		try {
//			this.socket = new Socket("localhost", 6179);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void update(Observable observable, Object data) {
//		try {
//			System.out.println("5秒后重启线程");
//			Thread.sleep(5000);
//		} catch (Exception e) {
//		}
//		// 重启线程
//		SimpleObservable simpleObservable = new SimpleObservable();
//		SimpleObserver simpleObserver = new SimpleObserver();
//		// 需要将观察者类加入到被观察者的观察者列表中
//		simpleObservable.addObserver(simpleObserver);
//		try {
//			simpleObservable.setBfi(socket.getInputStream());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		simpleObservable.thread.run();
//	}
//}
//
///**
// * 被观察者 线程 类
// */
//class SimpleObservable extends Observable implements Runnable {
//	private DataInputStream dis;
//	Thread thread;
//
//	public void setBfi(InputStream inputStream) {
//		dis = new DataInputStream(inputStream);
//	}
//
//	SimpleObservable() {
//		thread = new Thread(this);
//		System.out.println("A类被实例化了");
//	}
//
//	public void run() {
//		try {
//			int k = 0;
//			byte b[] = null;
//			String result = "";
//			while (true) {
//				System.out.println(k++);
//				int len = dis.readInt();
//				if (len < 1024) {
//					b = new byte[len];
//					dis.read(b);
//					result = new String(b, "UTF-8");
//					// System.out.println("服务器返回数据1：" + result);
//					b = null;
//				} else {
//					// 以1024字节为一个单位进行数据读取防止内存溢出
//					for (int i = 0; i < len; i = i + 1024) {
//						if (1024 + i > len) {
//							// System.out.println("len % 1024=" + len % 1024);
//							b = new byte[len % 1024];
//							dis.read(b);
//							result = result + new String(b, "UTF-8");
//						} else {
//							b = new byte[1024];
//							dis.read(b);
//							result = result + new String(b, "UTF-8");
//							// System.out.println("服务器返回数据1："
//							// + new String(b, "UTF-8") + "***end***");
//						}
//						b = null;
//					}
//				}
//				if (!result.contains("\"command_code\":\"5000002\"")) {
//					if (result.contains("\"command_code\":\"5000001\"")) {
////						System.out.println("接收到报警");
//						// DB操作
//						ZAlarmDao aDao = new ZAlarmDaoImp();
//						ObjectMapper mapper = JsonUtils.getInstance();
//						AlarmBean ab = mapper
//								.readValue(result, AlarmBean.class);
//						try {
//							long time = new SimpleDateFormat(
//									"yyyy-MM-dd HH:mm:ss").parse(
//									ab.getOccur_time()).getTime() / 1000;
//							
//							ZAlarm a = new ZAlarm(time, ab.getDistance(),
//									AlarmUtil.getDescription(ab.getAlarm_level()), ab.getAlarm_level(),
//									0, ab.getLast_time(),PropertyUtils.getStringByKey("host"));
//							aDao.addAlarm(a);
//						} catch (ParseException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					} else {
//						System.out.println("服务器返回数据1：" + result);
//					}
//				}
//				result = "";
//			}
//		} catch (SocketTimeoutException e) {
//			// e.printStackTrace();
//			// System.out.println("光纤连接异常，心跳包未得到正常回应。。。");
//			// **DB操作，记录连接异常**/
//			ZExceptionDao eDao = new ZExceptionDaoImp();
//			ZException z = new ZException(System.currentTimeMillis() / 1000, 0,
//					"光纤连接异常，心跳包未得到正常回应。。。",PropertyUtils.getStringByKey("host"));
//			eDao.addException(z);
//			// =================================
//			setChanged();
//			notifyObservers();
//		} catch (EOFException e) {
//			e.printStackTrace();
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
