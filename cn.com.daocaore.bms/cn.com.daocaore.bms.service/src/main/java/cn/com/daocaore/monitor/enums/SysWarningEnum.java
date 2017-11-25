package cn.com.daocaore.monitor.enums;

import lombok.Getter;
import qing.yun.hui.common.enums.ICommonEnum;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月23日下午2:46:55
 **/
public class SysWarningEnum {

	@Getter
	public enum Status implements ICommonEnum{
		//状态(0:未通知,1.通知中,2.通知失败,3.已通知)
		UN_NOTIFIED(0,"未通知"),
		NOTIFIED(1,"通知中"),
		FAIL_NOTIFIED(2,"通知失败"),
		HAVE_NOTIFIED(3,"已通知");
		
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
