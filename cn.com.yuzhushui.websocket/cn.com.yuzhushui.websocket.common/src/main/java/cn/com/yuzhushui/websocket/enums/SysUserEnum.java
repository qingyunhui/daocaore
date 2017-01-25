package cn.com.yuzhushui.websocket.enums;

import lombok.Getter;
import qing.yun.hui.common.enums.ICommonEnum;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月25日下午3:50:51
 **/
public class SysUserEnum {
	
	@Getter
	public enum UserType implements ICommonEnum{
//		用户类型：1:登陆用户，1:游客.
		LOGIN_USER(1,"登陆用户"),
		TOURISTS(2,"游客");
		private final int value;
	    private final String name;
	    
	    private UserType(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }
	    
		@Override
		public String getCode() {
			return String.valueOf(value);
		}
	}
}
