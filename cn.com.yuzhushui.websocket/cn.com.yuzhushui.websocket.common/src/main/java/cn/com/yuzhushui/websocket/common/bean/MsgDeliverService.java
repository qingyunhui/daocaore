package cn.com.yuzhushui.websocket.common.bean;

import java.util.Date;

/**
 * Created by zhuqiang on 2015/7/3 0003.
 * 服务器端 到 客户端 消息传递基类
 */
public class MsgDeliverService {
    /** 目标id */
    private Long targetId;
    /** 消息类型 */
    private Integer msgType;
    /** 消息内容 */
    private String msgBody;
    /** 服务器接受消息的时间 */
    private Date timeOfArrive;
    /** 发送者 */
    private MsgUserInfo msgUserInfo;

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public Date getTimeOfArrive() {
        return timeOfArrive;
    }

    public void setTimeOfArrive(Date timeOfArrive) {
        this.timeOfArrive = timeOfArrive;
    }

    public MsgUserInfo getMsgUserInfo() {
        return msgUserInfo;
    }

    public void setMsgUserInfo(MsgUserInfo msgUserInfo) {
        this.msgUserInfo = msgUserInfo;
    }
}
