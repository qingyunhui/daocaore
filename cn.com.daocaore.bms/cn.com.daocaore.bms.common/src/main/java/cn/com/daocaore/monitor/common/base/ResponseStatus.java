package cn.com.daocaore.monitor.common.base;

import lombok.Getter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月17日下午10:40:38
 **/
@Getter
public enum ResponseStatus {

	FAILED(0, "失败"), 
	SUCCESS(1, "成功");

	private final int value;
	private final String name;

	private ResponseStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}
	
}
