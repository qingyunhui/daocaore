package cn.com.yuzhushui.websocket.socket.handle;

import java.util.Date;
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

import cn.com.yuzhushui.websocket.common.bean.Code;
import cn.com.yuzhushui.websocket.common.bean.MsgUserInfo;
import cn.com.yuzhushui.websocket.common.bean.SessionUser;
import cn.com.yuzhushui.websocket.sys.biz.entity.SysUser;

/***
 ** @category websocket握手拦截器
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月22日下午3:52:48
 **/
public class MyHandshakeInterceptor implements HandshakeInterceptor {

    private static Logger logger = LoggerFactory.getLogger(MyHandshakeInterceptor.class);
    
    private Random random = new Random();
    //初次握手访问前
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        
    	logger.info("==================>初次，建立握手。");
    	
    	if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(true);
            if (session != null) {
                //使用userName区分WebSocketHandler，以便定向发送消息
                Object temp = session.getAttribute(Code.USER_SESSION);
                if(temp!= null){
                    if(temp instanceof SysUser){
                    	SysUser user = (SysUser)temp;
                        map.put(SessionManager.USER_SESSION,user);  //存入数据，方便在hander中获取
                    }
                }else{ 
                	//有session ，但是又没有user信息。就是游客
                    SessionUser user = new SessionUser();
                    MsgUserInfo msgUserInfo = new MsgUserInfo();
                    msgUserInfo.setUserId(new Date().getTime());
                    msgUserInfo.setNickname("游客" + random.nextInt(100000));
                    msgUserInfo.setUserType(Code.USER_TYPE_2);
                    msgUserInfo.setIcons("assets/common/image/1.jpg");
                    user.setMsgUserInfo(msgUserInfo);
                    map.put(SessionManager.USER_SESSION,user);
                    session.setAttribute(SessionManager.USER_SESSION,user);
                }
            }else{
                SessionUser user = new SessionUser();
                MsgUserInfo msgUserInfo = new MsgUserInfo();
                msgUserInfo.setUserId(new Date().getTime());
                msgUserInfo.setNickname("游客" + random.nextInt(100000));
                msgUserInfo.setUserType(Code.USER_TYPE_2);
                msgUserInfo.setIcons("assets/common/image/1.jpg");
                user.setMsgUserInfo(msgUserInfo);
                map.put(SessionManager.USER_SESSION,user);
            }
        }
    	logger.info("========================>初次，握手结束。");
        return true;
    }
    
//    beforeHandshake，在调用handler前处理方法。常用在注册用户信息，绑定WebSocketSession，在handler里根据用户信息获取WebSocketSession发送消息。

    //初次握手访问后
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
    	logger.info("========================>有人访问了{}",new Object[]{serverHttpRequest.getRemoteAddress()});
    }
}
