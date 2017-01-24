package cn.com.yuzhushui.websocket.common.bean;


/**
 * Created by zhuqiang on 2015/7/1 0001.
 */
public class Code {
    public static MsgUserInfo sysUser = new MsgUserInfo();
    static {
        sysUser.setIcons("assets/common/image/sys.gif");
        sysUser.setNickname("系统");
        sysUser.setUserId(0l);
    }

    /** 用户类型: 登录用户 **/
    public static final int USER_TYPE_1 = 1;
    /** 用户类型: 游客 **/
    public static final int USER_TYPE_2 = 2;
    /** session中的用户信息**/
    public static final String USER_SESSION = "user_session";
    /** 消息类型: 点对点 **/
    public static final int MSG_TYPE_1 = 1;
    /** 消息类型: 聊天室 **/
    public static final int MSG_TYPE_2 = 2;
    /** 消息类型: 服务推送消息，本地需要存储的用户信息 **/
    public static final int MSG_TYPE_3 = 3;
    /** 消息类型: 服务推送消息，在线列表和在线人数统计 **/
    public static final int MSG_TYPE_4 = 4;

    /** 消息类型: 特效事件 **/
    public static final int MSG_TYPE_5 = 5;

    /** 消息来源类型: 来自客户端 **/
    public static final int MSG_ORIGIN_CLIENT = 1;
    /** 消息来源类型: 来自服务器 **/
    public static final int MSG_ORIGIN_SERVICE = 2;

}
