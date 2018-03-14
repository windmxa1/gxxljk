//package org.socket;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.ConnectException;
//import java.net.Socket;
//import java.nio.ByteBuffer;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.websocket.OnClose;
//import javax.websocket.OnError;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.ServerEndpoint;
//
//import org.dao.ZGxHostDao;
//import org.dao.ZWebsocketCtlDao;
//import org.dao.imp.ZGxHostDaoImp;
//import org.dao.imp.ZWebsocketCtlDaoImp;
//import org.model.HostBean;
//import org.model.WaveBean;
//import org.model.ZGxHost;
//import org.model.ZWebsocketCtl;
//import org.tools.JsonUtils;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
////import com.opensymphony.xwork2.ActionContext;
//
//@ServerEndpoint("/ws/websocket")
//public class WebSocket2 {
//	private static SocketThread2 thread;
//	private static HostBean hb;
//
//	@OnMessage
//	public void onMessage(String message, Session session) {
//		System.out.println(message);
//
//		ObjectMapper mapper = JsonUtils.getInstance();
//		try {
//			hb = mapper.readValue(message, HostBean.class);
//			if (hb.getUserid() != null) {
//				thread = new SocketThread2(hb.getHost(), hb.getPort(), session,
//						hb.getUserid());
//				thread.start();
//			}
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
//
//	@OnError
//	public void onError(Session session, Throwable error) {
//		if (thread != null) {
//			thread.state = 0;
//		}
//		System.out.println("error:state=" + thread.state);
//		// error.printStackTrace();
//	}
//
//	@OnOpen
//	public void onOpen() {
//		System.out.println(" client connected ");
//	}
//
//	@OnClose
//	public void onClose() {
//		System.out.println(" client closed ");
//		if (thread != null) {
//			thread.state = 0;
//		}
//		System.out.println("close:state=" + thread.state);
//	}
//
//}
//
//class SocketThread2 extends Thread {
//	private String host;
//	private String port;
//	private Session session;
//	private Long userid;
//	public int state = 1;
//	private int enter = 0;
//	private int k = 0;
//	private Long threadId;
//
//	public SocketThread2(String host, String port, Session session, Long userid) {
//		this.host = host;
//		this.port = port;
//		this.session = session;
//		this.userid = userid;
//		this.threadId = System.currentTimeMillis();
//	}
//
//	public void run() {
//		System.out.println("running");
//		connect();
//	}
//
//	public void connect() {
//		// Long id = null;
//		// ZConnectCtl conCtl = null;
//		// ZConnectCtlDao cDao = null;
//		Socket socket = null;
//		DataOutputStream out = null;
//		DataInputStream dis = null;
//		try {
//			ZWebsocketCtlDao wDao = new ZWebsocketCtlDaoImp();
//			// ZWebsocketCtl websocketCtl = wDao.getWebsocketCtl(userid);
//			// 第一次进入轮询，如果关联为空，则new一个，不为空，则更新
//			if (enter == 0) {
//				wDao.saveOrupdate(host, userid);
//			}
//			enter = 1;
//			// 设置3秒延时,作为重新连接的缓冲
//			Thread.sleep(3 * 1000);
//			// 防止无用线程的出现，需要先检测数据表是否存在这条光纤，以防已删除光纤还继续进行无用的重连操作
//			ZGxHostDao gDao = new ZGxHostDaoImp();
//			if (gDao.getGxHost(host) == null
//					|| wDao.getWebsocketCtl(host, userid) == null) {
//				return;
//			}
//			if (socket == null) {
//				socket = new Socket(host, Integer.parseInt(port));
//			}
//			out = new DataOutputStream(socket.getOutputStream());
//			String json = "{\"command_code\":\"8000001\",\"description\":\"login\",\"seq_num\":\"0\",\"account\":\"ffrc\",\"password\":\"ffrc12345\"}";
//			// String json =
//			// "{\"command_code\":\"16001003\",\"description\":\"获取告警记录\",\"seq_num\":\"0\",\"start_date\":\"2015-01-10 01:00:00\",\"end_date\":\"2015-01-15 08:00:00\"}";
//			// System.out.println("客户端发送数据：" + json);
//			out.writeInt(json.getBytes().length);
//			// 在WINDOWS上不能转换成UTF-8，会导致发送中文数据时数据长度不对，程序崩溃
//			out.write(json.getBytes("UTF-8"));
//			// out.write(json.getBytes());
//			out.flush();
//			dis = new DataInputStream(socket.getInputStream());
//
//			// byte b[] = null;
//			ByteBuffer bb = null;
//			StringBuilder result = new StringBuilder();// 结果字符串
//
//			int l = 0;
//			while (state == 1) {// 关键在于每次轮询都必须与当前的关联进行比较
//				System.out.println(threadId);
//				ZWebsocketCtl socketCtl = wDao.getWebsocketCtl(userid);
//				if (l == 1) {// 非第一次进入轮询，关联不同与当前线程不同，则断开连接,相同则不做处理，说明用户还在查看当前光纤没有做更换
//					if (!socketCtl.getHost().equals(host)) {
//						break;
//					}
//				}
//				// System.out.println(k++);
//				int len = dis.readInt();
//				if (len < 1024) {
//					// b = new byte[len];
//					// dis.read(b);
//					// b = null;
//					bb = ByteBuffer.allocate(len);
//					dis.read(bb.array());
//					bb.clear();
//					// result = new String(b, "UTF-8");
//					// result.append(new String(b));
//					// System.out.println("服务器返回数据1：" + result);
//				} else {
//					// 以1024字节为一个单位进行数据读取防止内存溢出
//					for (int i = len; i > 0; i = i - 1024) {
//						if (i < 1024) {
//							bb = ByteBuffer.allocate(len);
//							dis.read(bb.array());
//							result.append(new String(bb.array(), "UTF-8"));
//							// System.out.println("len % 1024=" + len % 1024);
//							// b = new byte[len];
//							// if (dis.read(b) == -1) {
//							// break;
//							// }
//							// result.append(new String(b, "UTF-8"));
//							// System.out.println("服务器返回数据1："
//							// + new String(b, "UTF-8") + "***end***");
//						} else {
//							bb = ByteBuffer.allocate(1024);
//							dis.read(bb.array());
//							result.append(new String(bb.array(), "UTF-8"));
//							// b = new byte[1024];
//							// if (dis.read(b) == -1) {
//							// break;
//							// }
//							// result.append(new String(b, "UTF-8"));
//							// result.append(new String(b));
//							// System.out.println("1");
//							// System.out.println("服务器返回数据1："
//							// + new String(b, "UTF-8") + "***end***");
//						}
//						Thread.sleep(1);
//						bb.clear();
//						// b=null;
//					}
//				}
//				if (result.indexOf("\"command_code\":\"5000002\"") != -1) {// 将接收到的波形数据实时传输给前端
//					// System.out.println(result);
//					// System.out.println(result.length()==len);
//					// String pattern = "\"data\":.*]";
//					// Pattern r = Pattern.compile(pattern);
//					// Matcher m = r.matcher(result);
//					// m.find();
//					// String data = m.group().toString().replace("\"data\":",
//					// "");
//					// System.out.println(result);
//					// if (result.length() == len) {
//					ObjectMapper mapper = JsonUtils.getInstance();
//					try {
//						WaveBean waveBean = mapper.readValue(result.toString(),
//								WaveBean.class);
//						List<Float> list = waveBean.getData();
//
//						ZGxHost zGxHost = gDao.getGxHost(host);
//						Map<String, Object> map = new HashMap<String, Object>();
//						if (zGxHost != null) {
//							map.put("name", zGxHost.getName());
//						} else {
//							map.put("name", "该线路未命名");
//						}
//						map.put("list", list);
//						if (session != null && session.isOpen()) {
//							session.getBasicRemote().sendText(
//									mapper.writeValueAsString(map));
//						} else {
//							break;
//						}
//					} catch (Exception e) {
//						if (e instanceof JsonParseException) {
//							System.out.println("读取速度过快，Json转换异常");
//						}
//						System.out.println("转换异常");
//					}
//				}
//				// result = "";
//				result.setLength(0);
//				l = 1;
//				Thread.sleep(150);
//			}
//		} catch (ConnectException e) {
//			System.out.println("连接异常,3秒后重连");
//			k++;
//			if (k == 5) {
//				if (session != null && session.isOpen()) {
//					Map<String, Object> map = new HashMap<>();
//					map.put("name", "连接失败，请重新选择连接");
//					map.put("list", new ArrayList<>());
//					ObjectMapper mapper = JsonUtils.getInstance();
//					try {
//						session.getBasicRemote().sendText(
//								mapper.writeValueAsString(map));
//					} catch (JsonProcessingException e1) {
//						e1.printStackTrace();
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//				}
//			} else {
//				try {
//					if (socket != null & !socket.isClosed())
//						socket.close();
//					out.close();
//					dis.close();
//					socket = null;
//					out = null;
//					dis = null;
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//				connect();
//			}
//		} catch (IOException e) {
//			System.out.println("websocket服务器断连，3秒后自动重连");
//			try {
//				if (socket != null & !socket.isClosed())
//					socket.close();
//				out.close();
//				dis.close();
//				socket = null;
//				out = null;
//				dis = null;
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//			connect();
//		} catch (IllegalStateException e) {
//			System.out.println("illegalState");
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//			k = 0;
//			System.out.println("中断异常");
//		} finally {
//			System.out
//					.println("websocket over...websocket over...websocket over...");
//			try {
//				if (socket != null & !socket.isClosed())
//					socket.close();
//				out.close();
//				dis.close();
//				socket = null;
//				out = null;
//				dis = null;
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
//}
////