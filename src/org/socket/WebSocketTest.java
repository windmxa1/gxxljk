package org.socket;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.dao.ZConnectCtlDao;
import org.dao.ZGxHostDao;
import org.dao.ZWebsocketCtlDao;
import org.dao.imp.ZConnectCtlDaoImp;
import org.dao.imp.ZGxHostDaoImp;
import org.dao.imp.ZWebsocketCtlDaoImp;
import org.model.HostBean;
import org.model.WaveBean;
import org.model.ZConnectCtl;
import org.model.ZGxHost;
import org.model.ZWebsocketCtl;
//import org.model.ZUser;
import org.tools.JsonUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.Buffer;

//import com.opensymphony.xwork2.ActionContext;

@ServerEndpoint("/ws/websocket")
public class WebSocketTest {
	private static SocketThread thread;
	private static HostBean hb;

	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println(message);

		ObjectMapper mapper = JsonUtils.getInstance();
		try {
			hb = mapper.readValue(message, HostBean.class);
			if (hb.getUserid() != null) {
				thread = new SocketThread(hb.getHost(), hb.getPort(), session,
						hb.getUserid());
				thread.start();
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@OnError
	public void onError(Session session, Throwable error) {
		if (thread != null) {
			thread.state = 0;
		}
		error.printStackTrace();
	}

	@OnOpen
	public void onOpen() {
		System.out.println(" client connected ");
	}

	@OnClose
	public void onClose() {
		System.out.println(" connection closed ");
		if (thread != null) {
			thread.state = 0;
		}
	}

}

class SocketThread extends Thread {
	private String host;
	private String port;
	private Session session;
	private Long userid;
	public int state = 1;
	private int enter = 0;

	public SocketThread(String host, String port, Session session, Long userid) {
		this.host = host;
		this.port = port;
		this.session = session;
		this.userid = userid;
	}

	public void run() {
		System.out.println("running");
		connect();
	}

	public void connect() {
		// Long id = null;
		// ZConnectCtl conCtl = null;
		// ZConnectCtlDao cDao = null;
		try {
			ZWebsocketCtlDao wDao = new ZWebsocketCtlDaoImp();
			// ZWebsocketCtl websocketCtl = wDao.getWebsocketCtl(userid);
			// 第一次进入轮询，如果关联为空，则new一个，不为空，则更新
			if (enter == 0) {
				wDao.saveOrupdate(host, userid);
			}
			enter = 1;
			// 设置3秒延时,作为重新连接的缓冲
			Thread.sleep(3 * 1000);
			// 防止无用线程的出现，需要先检测数据表是否存在这条光纤，以防已删除光纤还继续进行无用的重连操作
			ZGxHostDao gDao = new ZGxHostDaoImp();
			if (gDao.getGxHost(host) == null
					|| wDao.getWebsocketCtl(host, userid) == null) {
				return;
			}
			Socket socket = new Socket(host, Integer.parseInt(port));
			DataOutputStream out = new DataOutputStream(
					socket.getOutputStream());
			String json = "{\"command_code\":\"8000001\",\"description\":\"login\",\"seq_num\":\"0\",\"account\":\"ffrc\",\"password\":\"ffrc12345\"}";
			// String json =
			// "{\"command_code\":\"16001003\",\"description\":\"获取告警记录\",\"seq_num\":\"0\",\"start_date\":\"2015-01-10 01:00:00\",\"end_date\":\"2015-01-15 08:00:00\"}";
//			System.out.println("客户端发送数据：" + json);
			out.writeInt(json.getBytes().length);
			// 在WINDOWS上不能转换成UTF-8，会导致发送中文数据时数据长度不对，程序崩溃
			out.write(json.getBytes("UTF-8"));
			// out.write(json.getBytes());
			out.flush();
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			int k = 0;
			byte b[] = null;
			StringBuilder result = new StringBuilder();// 结果字符串

			int l = 0;
			while (state == 1) {// 关键在于每次轮询都必须与当前的关联进行比较
				ZWebsocketCtl socketCtl = wDao.getWebsocketCtl(userid);
				if (l == 1) {// 非第一次进入轮询，关联不同与当前线程不同，则断开连接,相同则不做处理，说明用户还在查看当前光纤没有做更换
					if (!socketCtl.getHost().equals(host)) {
						break;
					}
				}
//				System.out.println(k++);
				int len = dis.readInt();
				if (len < 1024) {
					b = new byte[len];
					dis.read(b);
					// result = new String(b, "UTF-8");
					// result.append(new String(b));
					// System.out.println("服务器返回数据1：" + result);
					b = null;
				} else {
					// 以1024字节为一个单位进行数据读取防止内存溢出
					for (int i = 0; i < len; i = i + 1024) {
						if (1024 + i > len) {
							// System.out.println("len % 1024=" + len % 1024);
							b = new byte[len % 1024];
							dis.read(b);
							// result = result + new String(b, "UTF-8");
							result.append(new String(b, "UTF-8"));
							// System.out.println("0");
							// System.out.println("服务器返回数据1："
							// + new String(b, "UTF-8") + "***end***");
						} else {
							b = new byte[1024];
							dis.read(b);
							// result = result + new String(b, "UTF-8");
							result.append(new String(b, "UTF-8"));
							// result.append(new String(b));
							// System.out.println("1");
							// System.out.println("服务器返回数据1："
							// + new String(b, "UTF-8") + "***end***");
						}
						Thread.sleep(1);
						b = null;
					}
				}
				if (result.indexOf("\"command_code\":\"5000002\"") != -1) {// 将接收到的波形数据实时传输给前端
					// System.out.println(result);
					// System.out.println(result.length()==len);
					// String pattern = "\"data\":.*]";
					// Pattern r = Pattern.compile(pattern);
					// Matcher m = r.matcher(result);
					// m.find();
					// String data = m.group().toString().replace("\"data\":",
					// "");
					// System.out.println(result);
					// if (result.length() == len) {
					ObjectMapper mapper = JsonUtils.getInstance();
					WaveBean waveBean = mapper.readValue(result.toString(),
							WaveBean.class);
					ZGxHost zGxHost = gDao.getGxHost(host);
					Map<String, Object> map = new HashMap<String, Object>();
					if (zGxHost != null) {
						map.put("name", zGxHost.getName());
					} else {
						map.put("name", "该线路未命名");
					}
					List<Float> list = waveBean.getData();
					map.put("list", list);
					if (session != null && session.isOpen()) {
						session.getBasicRemote().sendText(
								mapper.writeValueAsString(map));
					} else {
						break;
					}
					// }
				}
				// result = "";
				result.setLength(0);
				l = 1;
			}
			// System.out.println("资源回收");
			out.close();
			dis.close();
			socket.close();
		} catch (ConnectException e) {
			System.out.println("连接异常,3秒后重连");
			connect();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO异常，3秒后重连");
			connect();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("中断异常");
		} finally {
		}
	}
}
