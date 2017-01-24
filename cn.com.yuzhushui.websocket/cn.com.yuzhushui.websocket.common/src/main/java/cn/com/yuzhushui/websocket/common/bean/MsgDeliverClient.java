package cn.com.yuzhushui.websocket.common.bean;

/**
 * Created by zhuqiang on 2015/7/3 0003.
 * 客户端的消息基类
 */
public class MsgDeliverClient {
    /** 目标id */
    private Long targetId;
    /** 消息类型 */
    private Integer msgType;
    /** 消息内容 */
    private String msgBody;

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
}
