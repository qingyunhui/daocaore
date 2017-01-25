package cn.com.yuzhushui.websocket.enums;

import lombok.Getter;
import qing.yun.hui.common.enums.ICommonEnum;

/***
 ** @category 聊天室聊天记录...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月25日下午1:51:25
 **/
public class ChatRoomRecordEnum {
	
	@Getter
	public enum MsgType implements ICommonEnum{
		//消息类型
		POINT_TO_POINT(1,"点对点"),
		CHAT_ROOM(2,"聊天室的消息"),
		SERVER_PUSH(3,"服务器推送"),
		REAL_TIME_UPDATE_ONLINE(4,"实时更新在线列表和在线人数统计"),
		SPECIAL_EFFECTS_EVENT(5,"特效事件");
		
		private final int value;
	    private final String name;
	    
	    private MsgType(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
	    
		@Override
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
	@Getter
	public enum MsgSource implements ICommonEnum{
		//消息来源
		CLIENT(0,"客户端"),
		SERVER(1,"服务端");
		
		private final int value;
	    private final String name;
	    
	    private MsgSource(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
	    
		@Override
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
}
