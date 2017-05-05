package cn.com.daocaore.bms.common.base;

import lombok.Data;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月17日下午10:24:29
 **/
@Data
public abstract class BaseForm<KEY_TYPE> {

	private String actionPath;//action路径
	
	private KEY_TYPE uuid;// 生成唯一标识
	
}
