package cn.com.yuzhushui.websocket.socket.handle;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import cn.com.yuzhushui.websocket.common.bean.Code;
import cn.com.yuzhushui.websocket.common.bean.MsgDeliverServiceRoom;
import cn.com.yuzhushui.websocket.common.bean.SessionUser;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月22日下午3:50:01
 **/
public class MyWebSocketHandler implements WebSocketHandler {

    private  final Logger logger=LoggerFactory.getLogger(MyWebSocketHandler.class);
    
    private SessionManager sessionManager = SessionManager.instance();

    //初次链接成功执行
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.debug("链接成功......");
        Object temp = session.getAttributes().get(SessionManager.USER_SESSION);
        if(temp!= null){
            SessionUser user = (SessionUser)temp; //用户信息
            Integer userType = user.getMsgUserInfo().getUserType();
            switch (userType) {
                case Code.USER_TYPE_1: //是用户
//                    userMap.put(userId, webSocketSession);
                    break;
                case Code.USER_TYPE_2: //是游客，自动加入系统聊天室,并且发送通知信息
                    MsgDeliverServiceRoom mdsr_userInfo = new MsgDeliverServiceRoom();
                    mdsr_userInfo.setMsgType(Code.MSG_TYPE_3);
                    mdsr_userInfo.setMsgUserInfo(user.getMsgUserInfo());
                    session.sendMessage(new TextMessage(JSONObject.toJSONString(mdsr_userInfo)));

                    sessionManager.chatroomMapPut(0l,user.getMsgUserInfo().getUserId(),session);
                    sessionManager.put(user.getMsgUserInfo().getUserId(),user.getMsgUserInfo().getUserType(),session);
                    user.getChatroomIds().add(0l);
                    MsgDeliverServiceRoom mdsr = new MsgDeliverServiceRoom();
                    mdsr.setMsgUserInfo(Code.sysUser);
                    mdsr.setTimeOfArrive(new Date());
                    mdsr.setTargetId(0l);
                    mdsr.setMsgBody(user.getMsgUserInfo().getNickname()  + "骑着鸵鸟进入了本聊天室.....");
                    mdsr.setMsgType(Code.MSG_TYPE_2);
                    SessionUser sys = new SessionUser();
                    sys.setMsgUserInfo(Code.sysUser);
                    sessionManager.sendMessage(sys,JSONObject.toJSONString(mdsr),Code.MSG_ORIGIN_SERVICE);

                    mdsr.setMsgType(Code.MSG_TYPE_4);
                    mdsr.setOnlieNum(sessionManager.getRoomOnlieNum(0l));
                    mdsr.setOnlieList(sessionManager.getRoomOnlieList(0l));
                    //推送在线人数和在线列表
                    sessionManager.sendMessage(sys,JSONObject.toJSONString(mdsr),Code.MSG_ORIGIN_SERVICE);
                    break;
                default:
                    break;
            }
        }
    }

    //接受消息处理消息
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        Map<String, Object> attributes = webSocketSession.getAttributes();
        SessionUser user = (SessionUser) attributes.get(SessionManager.USER_SESSION);
        sessionManager.sendMessage(user, webSocketMessage.getPayload() + "",Code.MSG_ORIGIN_CLIENT);
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if(webSocketSession.isOpen()){
            webSocketSession.close();
        }
        logger.debug("链接出错，关闭链接......");
        sessionManager.remove(webSocketSession);
        MsgDeliverServiceRoom mdsr = new MsgDeliverServiceRoom();
        mdsr.setMsgUserInfo(Code.sysUser);
        mdsr.setTimeOfArrive(new Date());
        mdsr.setTargetId(0l);
        SessionUser sys = new SessionUser();
        sys.setMsgUserInfo(Code.sysUser);
        mdsr.setMsgType(Code.MSG_TYPE_4);
        mdsr.setOnlieNum(sessionManager.getRoomOnlieNum(0l));
        mdsr.setOnlieList(sessionManager.getRoomOnlieList(0l));
        //推送在线人数和在线列表
        sessionManager.sendMessage(sys,JSONObject.toJSONString(mdsr),Code.MSG_ORIGIN_SERVICE);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        logger.debug("链接关闭......" + closeStatus.toString());
        sessionManager.remove(webSocketSession);
        MsgDeliverServiceRoom mdsr = new MsgDeliverServiceRoom();
        mdsr.setMsgUserInfo(Code.sysUser);
        mdsr.setTimeOfArrive(new Date());
        mdsr.setTargetId(0l);
        SessionUser sys = new SessionUser();
        sys.setMsgUserInfo(Code.sysUser);
        mdsr.setMsgType(Code.MSG_TYPE_4);
        mdsr.setOnlieNum(sessionManager.getRoomOnlieNum(0l));
        mdsr.setOnlieList(sessionManager.getRoomOnlieList(0l));
        //推送在线人数和在线列表
        sessionManager.sendMessage(sys,JSONObject.toJSONString(mdsr),Code.MSG_ORIGIN_SERVICE);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    
}
