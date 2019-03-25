package org.listener;

import javax.websocket.Session;

public interface MyListener {
	void onReadMessage(String message, String host,Session session);

	void onConnected(String host, boolean b);

}
