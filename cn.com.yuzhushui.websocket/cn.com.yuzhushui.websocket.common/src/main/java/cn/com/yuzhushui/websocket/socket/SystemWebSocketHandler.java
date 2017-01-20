package cn.com.yuzhushui.websocket.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
  
@Component
/**3.实现服务端的消息接受和推送：*/
public class SystemWebSocketHandler implements WebSocketHandler {  
        
	Logger logger=LoggerFactory.getLogger(SystemWebSocketHandler.class);
      
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();;  
   
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {  
    	logger.info("===========afterConnectionEstablished");  
        users.add(session);  
        session.sendMessage(new TextMessage("connect"));  
        session.sendMessage(new TextMessage("new_msg"));  
    }  
   
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {  
        logger.info("===========handleMessage"+ message.toString());  
        session.sendMessage(new TextMessage(new Date() + ""));  
    }  
   
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {  
        if(session.isOpen()){  
            session.close();  
        }  
        logger.info("===========删除一个session【handleTransportError】...");  
        users.remove(session);  
    }  
   
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception { 
    	logger.info("===========删除一个session【afterConnectionClosed】...");  
        users.remove(session);  
    }  
   
    public boolean supportsPartialMessages() {  
        return false;  
    }  
   
    /** 
     * 给所有在线用户发送消息 
     * @param message 
     */  
    public void sendMessageToUsers(TextMessage message) {  
        for (WebSocketSession user : users) {  
            try {  
                if (user.isOpen()) {  
                    user.sendMessage(message);  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}  
