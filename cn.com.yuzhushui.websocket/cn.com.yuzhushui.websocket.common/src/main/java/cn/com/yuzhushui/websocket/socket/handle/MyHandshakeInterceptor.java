package cn.com.yuzhushui.websocket.socket.handle;

import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;

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
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月22日下午3:52:48
 **/
public class MyHandshakeInterceptor implements HandshakeInterceptor {

    private static Logger logger = LoggerFactory.getLogger(HandshakeInterceptor.class);
    
    private Random random = new Random();
    //初次握手访问前
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
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
                }else{ //有session ，但是又没有user信息。就是游客
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
                session.setAttribute(SessionManager.USER_SESSION,user);
            }
        }
        return true;
    }

    //初次握手访问后
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        System.err.println("有人访问了：" + serverHttpRequest.getRemoteAddress());
    }
    
}
