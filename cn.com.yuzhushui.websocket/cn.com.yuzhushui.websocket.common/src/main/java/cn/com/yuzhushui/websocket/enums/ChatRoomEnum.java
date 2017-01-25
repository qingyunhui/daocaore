package cn.com.yuzhushui.websocket.enums;

import lombok.Getter;
import qing.yun.hui.common.enums.ICommonEnum;

/***
 ** @category 聊天室对应的枚举类...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月25日上午11:57:51
 **/
public class ChatRoomEnum {
	
	@Getter
	public enum Status implements ICommonEnum{
		
//		聊天室状态：0.待审核、1.审核不通过、2.审核通过、3.禁用
		
		UN_AUDIT(0,"待审核"),
		UN_PASS(1,"审核不通过"),
		AUDIT_PASS(2,"通过"),
		DISABLE(3,"禁用");
		
		private final int value;
	    private final String name;
	    
	    private Status(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
	    
		@Override
		public String getCode() {
			return String.valueOf(value);
		}
	}
}
