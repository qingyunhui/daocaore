package cn.com.yuzhushui.websocket.enums;

import lombok.Getter;
import qing.yun.hui.common.enums.ICommonEnum;

/***
 ** @category 用户加入的聊天室列表枚举类...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月25日下午1:42:44
 **/
public class ChatRoomJoinEnum {

	@Getter
	public enum Classify implements ICommonEnum{
		
		//聊天室分类(同学、亲友、聚会)
		
		SCHOOLMATE(0,"同学"),
		FRIEND(1,"好友"),
		GATHERING(2,"聚会");
		
		private final int value;
	    private final String name;
	    
	    private Classify(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
	    
		@Override
		public String getCode() {
			return String.valueOf(value);
		}
	}
}
