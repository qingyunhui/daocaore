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
import cn.com.yuzhushui.websocket.enums.ChatRoomRecordEnum;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category websocket处理类
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
        logger.debug("===============>>>链接成功......");
        Object object = session.getAttributes().get(SessionManager.USER_SESSION);
        if(null!=object){
            SessionUser sessionUser = (SessionUser)object; //用户信息
            Integer userType = sessionUser.getMsgUserInfo().getUserType();
            switch (userType) {
                case Code.USER_TYPE_1: //是用户
//                    userMap.put(userId, webSocketSession);
                    break;
                case Code.USER_TYPE_2: //是游客，自动加入系统聊天室,并且发送通知信息
                    MsgDeliverServiceRoom mdsr_userInfo = new MsgDeliverServiceRoom();
                    mdsr_userInfo.setMsgType(ChatRoomRecordEnum.MsgType.SERVER_PUSH.getValue());
                    mdsr_userInfo.setMsgUserInfo(sessionUser.getMsgUserInfo());
                    session.sendMessage(new TextMessage(JSONObject.toJSONString(mdsr_userInfo)));

                    sessionManager.chatroomMapPut(0l,sessionUser.getMsgUserInfo().getUserId(),session);
                    sessionManager.put(sessionUser.getMsgUserInfo().getUserId(),sessionUser.getMsgUserInfo().getUserType(),session);
                    sessionUser.getChatroomIds().add(0l);
                    MsgDeliverServiceRoom mdsr = new MsgDeliverServiceRoom();
                    mdsr.setMsgUserInfo(Code.sysUser);
                    mdsr.setTimeOfArrive(new Date());
                    mdsr.setTargetId(0l);
                    mdsr.setMsgBody(sessionUser.getMsgUserInfo().getNickname()  + "骑着鸵鸟进入了本聊天室.....");
                    mdsr.setMsgType(ChatRoomRecordEnum.MsgType.CHAT_ROOM.getValue());
                    SessionUser sys = new SessionUser();
                    sys.setMsgUserInfo(Code.sysUser);
                    sessionManager.sendMessage(sys,JSONObject.toJSONString(mdsr),ChatRoomRecordEnum.MsgSource.SERVER.getValue());

                    mdsr.setMsgType(ChatRoomRecordEnum.MsgType.REAL_TIME_UPDATE_ONLINE.getValue());
                    mdsr.setOnlieNum(sessionManager.getRoomOnlieNum(0l));
                    mdsr.setOnlieList(sessionManager.getRoomOnlieList(0l));
                    //推送在线人数和在线列表
                    sessionManager.sendMessage(sys,JSONObject.toJSONString(mdsr),ChatRoomRecordEnum.MsgSource.SERVER.getValue());
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
        sessionManager.sendMessage(user, webSocketMessage.getPayload() + "",ChatRoomRecordEnum.MsgSource.CLIENT.getValue());
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
        mdsr.setMsgType(ChatRoomRecordEnum.MsgType.REAL_TIME_UPDATE_ONLINE.getValue());
        mdsr.setOnlieNum(sessionManager.getRoomOnlieNum(0l));
        mdsr.setOnlieList(sessionManager.getRoomOnlieList(0l));
        //推送在线人数和在线列表
        sessionManager.sendMessage(sys,JSONObject.toJSONString(mdsr),ChatRoomRecordEnum.MsgSource.SERVER.getValue());
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
        mdsr.setMsgType(ChatRoomRecordEnum.MsgType.REAL_TIME_UPDATE_ONLINE.getValue());
        mdsr.setOnlieNum(sessionManager.getRoomOnlieNum(0l));
        mdsr.setOnlieList(sessionManager.getRoomOnlieList(0l));
        //推送在线人数和在线列表
        sessionManager.sendMessage(sys,JSONObject.toJSONString(mdsr),ChatRoomRecordEnum.MsgSource.SERVER.getValue());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    
}
