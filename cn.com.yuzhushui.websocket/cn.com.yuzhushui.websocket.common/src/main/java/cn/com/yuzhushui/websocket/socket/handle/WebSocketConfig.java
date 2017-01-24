package cn.com.yuzhushui.websocket.socket.handle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/***
 ** @category Websocet服务端实现类，并且该类，必须要被springmvc扫描到...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月22日下午3:47:34
 **/
@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
	
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    	//用来注册websocket server实现类，第二个参数是访问websocket的地址
        registry.addHandler(myWebSocketHandler(),"/socketService").addInterceptors(new MyHandshakeInterceptor());//支持websocket 的访问链接
        //这个是使用Sockjs的注册方法
        registry.addHandler(myWebSocketHandler(), "/sockjs/socketService").addInterceptors(new MyHandshakeInterceptor()).withSockJS();//不支持websocket的访问链接
    }
    @Bean
    public WebSocketHandler myWebSocketHandler(){
        return new MyWebSocketHandler();
    }
//    @Configuration
//    @EnableWebMvc
//    @EnableWebSocket
//    这三个大致意思是使这个类支持以@Bean的方式加载bean，并且支持springmvc和websocket，不是很准确大致这样，试了一下@EnableWebMvc不加也没什么影响，@Configuration本来就支持springmvc的自动扫描
    
}
