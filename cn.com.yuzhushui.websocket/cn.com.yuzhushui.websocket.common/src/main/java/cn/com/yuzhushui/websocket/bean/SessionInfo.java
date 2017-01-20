package cn.com.yuzhushui.websocket.bean;

import lombok.Data;

/***
 ** @Description: 请用一句话来描述
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Oct 27, 2015 9:23:57 AM
 ** @version: V1.0
 ***/
@Data
public class SessionInfo<T,V> {
	
	private T sysUser;
	
	private V sysAccount;
}
