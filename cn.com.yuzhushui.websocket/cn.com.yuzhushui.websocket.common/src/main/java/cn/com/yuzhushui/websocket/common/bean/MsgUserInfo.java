package cn.com.yuzhushui.websocket.common.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by zhuqiang on 2015/7/3 0003.
 * 消息传递中的 用户信息
 */
@Getter
@Setter
public class MsgUserInfo {
    /** 用户id */
    private Long userId;
    /** 昵称*/
    private String nickname;
    /** 头像 */
    private String icons;
    private Integer userType;

}
