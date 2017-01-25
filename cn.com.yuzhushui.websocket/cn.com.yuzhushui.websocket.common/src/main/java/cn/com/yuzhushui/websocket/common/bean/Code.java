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

}
