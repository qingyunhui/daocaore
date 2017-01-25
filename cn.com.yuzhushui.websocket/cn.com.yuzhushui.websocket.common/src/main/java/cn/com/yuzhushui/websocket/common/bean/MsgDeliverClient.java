package cn.com.yuzhushui.websocket.common.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by zhuqiang on 2015/7/3 0003.
 * 客户端的消息基类
 */
@Getter
@Setter
public class MsgDeliverClient {
	
    /** 目标id */
    private Long targetId;
    /** 消息类型 */
    private Integer msgType;
    /** 消息内容 */
    private String msgBody;

}
