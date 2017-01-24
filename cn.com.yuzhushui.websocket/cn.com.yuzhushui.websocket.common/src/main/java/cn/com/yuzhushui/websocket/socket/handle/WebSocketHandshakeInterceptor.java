package cn.com.yuzhushui.websocket.socket.handle;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import qing.yun.hui.common.utils.StringUtil;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月22日下午3:52:48
 **/
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    private static Logger logger = LoggerFactory.getLogger(HandshakeInterceptor.class);
    
    public static final String SESSION_USERNAME = "username";
    
    public static final String WEBSOCKET_USERNAME = "websocket_username";
    
    private Random random = new Random();
    
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
    	logger.info("-------->WebSocketHandshakeInterceptor.beforeHandshake()");
    	if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                //使用userName区分WebSocketHandler，以便定向发送消息
                String userName = (String) session.getAttribute(SESSION_USERNAME);
                if(!StringUtil.isEmpty(userName)){
                	attributes.put(WEBSOCKET_USERNAME,userName);
                }else{
                	attributes.put(WEBSOCKET_USERNAME,"游客【"+random.nextInt(100000)+800000+"】");
                }
            }else{
            	attributes.put(WEBSOCKET_USERNAME,"游客【"+random.nextInt(100000)+"】");
            }
        }
        return true;
    }

    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    	logger.info("-------->WebSocketHandshakeInterceptor.afterHandshake()");
    }
}
