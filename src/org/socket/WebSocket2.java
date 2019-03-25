package org.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.dao.ZGxHostDao;
import org.dao.ZWebsocketCtlDao;
import org.dao.imp.ZGxHostDaoImp;
import org.dao.imp.ZWebsocketCtlDaoImp;
import org.listener.MyListener;
import org.model.HostBean;
import org.model.WaveBean;
import org.model.ZGxHost;
import org.model.ZWebsocketCtl;
import org.netty.HeartBeatsClient;
import org.tools.Constans;
import org.tools.JsonUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.opensymphony.xwork2.ActionContext;

@ServerEndpoint("/ws/websocket")
public class WebSocket2 implements MyListener {

	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println(message);
		ObjectMapper mapper = JsonUtils.getInstance();
		try {
			HostBean hb = mapper.readValue(message, HostBean.class);
			if (hb.getUserid() != null) {
				new HeartBeatsClient(this, hb.getUserid()).connect(
						Integer.parseInt(hb.getPort()), hb.getHost());
				Constans.inputUSER_SESSION_MAP(hb.getUserid(), session);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println(" client onerror ");
		error.printStackTrace();
	}

	@OnOpen
	public void onOpen() {
		System.out.println(" client connected ");
	}

	@OnClose
	public void onClose() {
		System.out.println(" client closed ");
	}

	@Override
	public void onReadMessage(String result, String host, Session session) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = JsonUtils.getInstance();
		try {
			WaveBean waveBean = mapper.readValue(result.toString(),
					WaveBean.class);
			List<Float> list = waveBean.getData();
			ZGxHostDao gDao = new ZGxHostDaoImp();
			ZGxHost zGxHost = gDao.getGxHost(host);
			Map<String, Object> map = new HashMap<String, Object>();
			if (zGxHost != null) {
				map.put("name", zGxHost.getName());
			} else {
				map.put("name", "该线路未命名");
			}
			map.put("list", list);
			if (session != null && session.isOpen()) {
				session.getBasicRemote().sendText(
						mapper.writeValueAsString(map));
			}
		} catch (Exception e) {
			if (e instanceof JsonParseException) {
				System.out.println("读取速度过快，Json转换异常");
			}
			System.out.println("转换异常");
		}
	}

	@Override
	public void onConnected(String host, boolean b) {
		// TODO Auto-generated method stub

	}

}
