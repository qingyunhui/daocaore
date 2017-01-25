package cn.com.yuzhushui.websocket.common.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by zhuqiang on 2015/7/3 0003.
 * 服务器端 到 客户端 消息传递基类
 */
@Getter
@Setter
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

}
