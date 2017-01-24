package cn.com.yuzhushui.websocket.common.bean;

import cn.com.yuzhushui.websocket.sys.biz.entity.SysUser;

import java.util.ArrayList;

/**
 * Created by zhuqiang on 2015/7/2 0002.
 */
public class SessionUser extends SysUser {
    /** 在运行期间 该用户所加入的聊天室id*/
    private ArrayList<Long> chatroomIds = new ArrayList<>();
    /** 消息传递中的用户信息*/
    private MsgUserInfo msgUserInfo;

    public ArrayList<Long> getChatroomIds() {
        return chatroomIds;
    }

    public void setChatroomIds(ArrayList<Long> chatroomIds) {
        this.chatroomIds = chatroomIds;
    }

    public MsgUserInfo getMsgUserInfo() {
        return msgUserInfo;
    }

    public void setMsgUserInfo(MsgUserInfo msgUserInfo) {
        this.msgUserInfo = msgUserInfo;
    }
}
