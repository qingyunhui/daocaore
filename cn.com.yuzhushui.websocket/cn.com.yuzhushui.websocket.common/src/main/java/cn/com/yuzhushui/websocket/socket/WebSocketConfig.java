package cn.com.yuzhushui.websocket.socket;

/**
 * 注册服务类
 * */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration  
@EnableWebMvc  
@EnableWebSocket  
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{  
  
	Logger logger=LoggerFactory.getLogger(WebSocketConfig.class);
	
	@Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {  
		logger.info("========开始注册websocket服务...");
        registry.addHandler(systemWebSocketHandler(),"/webSocketServer");  
        registry.addHandler(systemWebSocketHandler(),"/webSocketServer/sockjs").setAllowedOrigins("*").withSockJS();
        logger.info("========注册websocket服务结束...");
    }  
      
    @Bean
    public WebSocketHandler systemWebSocketHandler(){  
        return new SystemWebSocketHandler();  
    }  
}