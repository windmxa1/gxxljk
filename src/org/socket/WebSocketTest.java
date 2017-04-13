package org.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.dao.ZGxHostDao;
import org.dao.imp.ZGxHostDaoImp;
import org.model.AlarmBean;
import org.model.HostBean;
import org.model.ZGxHost;
import org.tools.JsonUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ServerEndpoint("/ws/websocket")
public class WebSocketTest {
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println(message);
		ObjectMapper mapper = JsonUtils.getInstance();
		try {
			HostBean hb = mapper.readValue(message, HostBean.class);
			SocketThread thread = new SocketThread(hb.getHost(), hb.getPort(),
					session);
			thread.start();
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

	@OnOpen
	public void onOpen() {
		System.out.println(" client connected ");
	}

	@OnClose
	public void onClose() {
		System.out.println(" connection closed ");
	}

}

class SocketThread extends Thread {
	private String host;
	private String port;
	private Session session;

	public SocketThread(String host, String port, Session session) {
		this.host = host;
		this.port = port;
		this.session = session;
	}

	public void run() {
		System.out.println("running");
		try {
			Socket socket = new Socket(host, Integer.parseInt(port));
			DataOutputStream out = new DataOutputStream(
					socket.getOutputStream());
			String json = "{\"command_code\":\"8000001\",\"description\":\"login\",\"seq_num\":\"0\",\"account\":\"ffrc\",\"password\":\"ffrc12345\"}";
			// String json =
			// "{\"command_code\":\"16001003\",\"description\":\"获取告警记录\",\"seq_num\":\"0\",\"start_date\":\"2015-01-10 01:00:00\",\"end_date\":\"2015-01-15 08:00:00\"}";
			System.out.println("客户端发送数据：" + json);
			out.writeInt(json.getBytes().length);
			// 在WINDOWS上不能转换成UTF-8，会导致发送中文数据时数据长度不对，程序崩溃
			// out.write(json.getBytes("UTF-8"));
			out.write(json.getBytes());
			out.flush();
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			int k = 0;
			byte b[] = null;
			String result = "";
			while (true) {
				System.out.println(k++);
				int len = dis.readInt();
				if (len < 1024) {
					b = new byte[len];
					dis.read(b);
					result = new String(b, "UTF-8");
					// System.out.println("服务器返回数据1：" + result);
					b = null;
				} else {
					// 以1024字节为一个单位进行数据读取防止内存溢出
					for (int i = 0; i < len; i = i + 1024) {
						if (1024 + i > len) {
							// System.out.println("len % 1024=" + len % 1024);
							b = new byte[len % 1024];
							dis.read(b);
							result = result + new String(b, "UTF-8");
						} else {
							b = new byte[1024];
							dis.read(b);
							result = result + new String(b, "UTF-8");
							// System.out.println("服务器返回数据1："
							// + new String(b, "UTF-8") + "***end***");
						}
						b = null;
					}
				}
				if (result.contains("\"command_code\":\"5000002\"")) {// 将接收到的波形数据实时传输给前端
					String pattern = "\"data\":.*]";
					Pattern r = Pattern.compile(pattern);
					Matcher m = r.matcher(result);
					m.find();
					String data = m.group().toString().replace("\"data\":", "");
					ObjectMapper mapper = JsonUtils.getInstance();
					ZGxHostDao gDao = new ZGxHostDaoImp();
					ZGxHost zGxHost = gDao.getGxHost(host);
					Map<String, Object> map = new HashMap<String, Object>();
					if(zGxHost!=null){
						map.put("name", zGxHost.getName());
					}else {
						map.put("name", "该线路未命名");
					}
					List<Integer> list = mapper.readValue(data, List.class);
					map.put("list", list);
					session.getBasicRemote().sendText(mapper.writeValueAsString(map));
				}
				result = "";
			}
		} catch (ConnectException e) {
			System.out.println("连接异常");
		} catch (IOException e) {
			System.out.println("IO异常");
		}

	}
}
