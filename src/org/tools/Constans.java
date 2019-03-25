package org.tools;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

import org.apache.struts2.ServletActionContext;

public class Constans {
	/**
	 * 适应任何系统的文件分隔符
	 */
	public static final String dot = File.separator;
	public static final String pdfDir = ServletActionContext.getRequest()
			.getRealPath("/") + "pdf" + dot;
	public static final String pdfUrl = "http://"
			+ ServletActionContext.getRequest().getLocalAddr() + ":"
			+ ServletActionContext.getRequest().getLocalPort()
			+ ServletActionContext.getRequest().getContextPath() + "/" + "pdf/";
	public static final String watermark = ServletActionContext.getRequest()
			.getRealPath("/") + "watermark.jpg";

	public static ConcurrentHashMap<Long, Integer> USER_SOCKET_MAP = new ConcurrentHashMap<>();
	public static ConcurrentHashMap<Integer, Long> SOCKET_USER_MAP = new ConcurrentHashMap<>();
	public static ConcurrentHashMap<Long, Session> USER_SESSION_MAP = new ConcurrentHashMap<>();

	public static void inputUSER_SOCKET_MAP(Long key, Integer newValue) {
		Integer oldValue = USER_SOCKET_MAP.get(key);
		if (oldValue == null) {
			USER_SOCKET_MAP.putIfAbsent(key, newValue);
		}else{
			USER_SOCKET_MAP.replace(key, oldValue, newValue);
		}
	}

	public static void inputSOCKET_USER_MAP(Integer key,Long newValue) {
		Long oldValue = SOCKET_USER_MAP.get(key);
		if (oldValue == null) {
			SOCKET_USER_MAP.putIfAbsent(key, newValue);
		}else{
			SOCKET_USER_MAP.replace(key, oldValue, newValue);
		}
	}

	public static void inputUSER_SESSION_MAP(Long key ,Session newValue) {
		Session oldValue = USER_SESSION_MAP.get(key);
		if (oldValue == null) {
			USER_SESSION_MAP.putIfAbsent(key, newValue);
		}else{
			USER_SESSION_MAP.replace(key, oldValue, newValue);
		}
	}
}
