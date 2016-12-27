package cn.com.yuzhushui.schedule.enums;

import lombok.Getter;
import qing.yun.hui.common.enums.ICommonEnum;

/***
 ** @category 
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月22日下午11:34:49
 **/
public class SysAccountEnum{

	@Getter
	public enum STATUS implements ICommonEnum{
		//'状态(0.未审核、1.审核不通过、2.审核通过、3.停用)',
		UN_AUDIT(0,"未审核"),
		AUDIT_FAIL(1,"审核不通过"),
		AUDIT_SUCCESS(2,"审核通过"),
		DISABLED(3,"禁用");
		
		private final int value;
	    private final String name;
	    
	    private STATUS(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }

		public String getCode() {
			return String.valueOf(value);
		}
	}
}
