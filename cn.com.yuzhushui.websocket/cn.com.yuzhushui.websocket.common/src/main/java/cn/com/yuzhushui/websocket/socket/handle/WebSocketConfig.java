package cn.com.yuzhushui.websocket.socket.handle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/***
 ** @category Websocet服务端实现类，并且该类，必须要被springmvc扫描到(websocket的入口)...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月22日下午3:47:34
 **/
//@EnableWebMvc//这个标注可以不加，如果有加，要extends WebMvcConfigurerAdapter
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	
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


/*
 * 1.实现WebSocketConfigurer接口，重写registerWebSocketHandlers方法，这是一个核心实现方法，配置websocket入口，允许访问的域、注册Handler、SockJs支持和拦截器。
 * 2.registry.addHandler注册和路由的功能，当客户端发起websocket连接，把/path交给对应的handler处理，而不实现具体的业务逻辑，可以理解为收集和任务分发中心。
 * 3.setAllowedOrigins(String[] domains),允许指定的域名或IP(含端口号)建立长连接，如果只允许自家域名访问，这里轻松设置。如果不限时使用"*"号，如果指定了域名，则必须要以http或https开头。
 * 4.addInterceptors，顾名思义就是为handler添加拦截器，可以在调用handler前后加入我们自己的逻辑代码。
 * 5.spring websocket也支持STOMP协议，下回再分享。
*/
