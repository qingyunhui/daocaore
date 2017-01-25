package cn.com.yuzhushui.websocket.enums;

import lombok.Getter;
import qing.yun.hui.common.enums.ICommonEnum;

/***
 ** @category 聊天室在线列表...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月25日下午1:48:02
 **/
public class ChatRoomOnlinesEnum {

	@Getter
	public enum OnlineStatus implements ICommonEnum{
		
		//在线状态(0:离线，1：在线，2.未知)
		
		OFF_LINE(0,"离线"),
		ON_LINE(1,"在线"),
		UNKNOWN(2,"未知");
		
		private final int value;
	    private final String name;
	    
	    private OnlineStatus(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
	    
		@Override
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
}
