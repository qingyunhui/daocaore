package cn.com.yuzhushui.websocket.socket;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;

public class AlarmHander implements WebSocketHandler {
	
	private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>(); 
	
	Logger logger=LoggerFactory.getLogger(AlarmHander.class);
	
	public void afterConnectionEstablished(WebSocketSession session)throws Exception {
		users.add(session);  
		logger.info("ConnectionEstablished"+"=>当前在线用户的数量是:"+users.size());
	}

	public void handleMessage(WebSocketSession session,WebSocketMessage<?> message) throws Exception {
		 logger.info(JSONObject.toJSONString(message.getPayload()));  
	     TextMessage returnMessage = new TextMessage("received at server");  
	     sendMessageToUsers(returnMessage);        
	}

	public void handleTransportError(WebSocketSession session,Throwable exception) throws Exception {
		if(session.isOpen()){  
            session.close();  
        }  
        users.remove(session); 
	}

	public void afterConnectionClosed(WebSocketSession session,CloseStatus closeStatus) throws Exception {
		users.remove(session);  
		logger.info("ConnectionClosed"+"=>当前在线用户的数量是:"+users.size()); 
	}

	public boolean supportsPartialMessages() {
		return false;
	}
	
	public void sendMessageToUsers(TextMessage message) {  
        for (WebSocketSession user : users) {  
            if (user.isOpen()) {  
                try {  
                    for(int i = 0;i < 5;i++ ){  
                        user.sendMessage(message);  
                    }  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }
}
