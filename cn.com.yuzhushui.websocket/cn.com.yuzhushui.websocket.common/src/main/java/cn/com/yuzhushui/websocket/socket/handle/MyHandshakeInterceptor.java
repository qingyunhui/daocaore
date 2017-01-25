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

import cn.com.yuzhushui.websocket.common.bean.MsgUserInfo;
import cn.com.yuzhushui.websocket.common.bean.SessionUser;
import cn.com.yuzhushui.websocket.enums.SysUserEnum;
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
            if (null!=session) {
                Object object = session.getAttribute(SessionManager.USER_SESSION);
                if(null!=object && object instanceof SysUser){
                	SessionUser sessionUser = (SessionUser)object;
                    map.put(SessionManager.USER_SESSION,sessionUser);  //存入数据，方便在handler中获取
                }else{ 
                	//有session ，但是又没有user信息。就是游客
                    SessionUser user = new SessionUser();
                    MsgUserInfo msgUserInfo = new MsgUserInfo();
                    msgUserInfo.setUserId(new Date().getTime());
                    msgUserInfo.setNickname("游客" + random.nextInt(100000));
                    msgUserInfo.setUserType(SysUserEnum.UserType.TOURISTS.getValue());
                    msgUserInfo.setIcons("assets/common/image/1.jpg");
                    user.setMsgUserInfo(msgUserInfo);
                    map.put(SessionManager.USER_SESSION,user);
                    session.setAttribute(SessionManager.USER_SESSION,user);
                }
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
