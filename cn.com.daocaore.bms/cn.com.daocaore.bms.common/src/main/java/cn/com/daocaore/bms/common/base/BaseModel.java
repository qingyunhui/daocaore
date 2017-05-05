package cn.com.daocaore.bms.common.base;
/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月17日下午10:20:23
 **/
public abstract class BaseModel <KEY_TYPE>{

	@SuppressWarnings("unused")
	private KEY_TYPE id;
	
	public abstract KEY_TYPE getId();
	
}
