package com.tw.webSocket;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
@Component
public class SystemWebSocketHandler implements WebSocketHandler{

	private static final ArrayList<WebSocketSession> users;
	static{
		users = new ArrayList<WebSocketSession>();
	}
	
	public SystemWebSocketHandler(){
		
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
			throws Exception {
		users.remove(session);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		users.add(session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)
			throws Exception {
		TextMessage tm = new TextMessage(message.getPayload()+"");
		session.sendMessage(tm);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable throwable)
			throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		users.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给所有在线用户发送消息
	 * @param message
	 */
	public void sendMessageToUsers(TextMessage message) {
		for (WebSocketSession user : users) {
			if (user.isOpen()) {
				try {
					user.sendMessage(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void sendMessageToUser(String username,TextMessage message) {
		for (WebSocketSession user : users) {
			if (user.getAttributes().get("username").equals(username)) {
				try {
					if (user.isOpen()) {
						user.sendMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
