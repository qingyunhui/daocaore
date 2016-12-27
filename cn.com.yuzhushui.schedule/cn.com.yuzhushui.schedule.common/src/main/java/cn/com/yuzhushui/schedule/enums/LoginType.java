package cn.com.yuzhushui.schedule.enums;

import lombok.Getter;
import qing.yun.hui.common.enums.ICommonEnum;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月23日下午11:09:05
 **/
@Getter
public enum LoginType implements ICommonEnum{

	//'登陆类型(0.账号、1.手机号、2.邮箱、)',
	ACCOUNT_TYPE(0,"账号登陆"),
	MOBILEPHONE_TYPE(1,"手机号登陆"),
	EMAIL_TYPE(2,"邮箱登陆");
	
	private final int value;
    private final String name;
    
    private LoginType(int value, String name) {
        this.value = value;
        this.name = name;
    }

	public String getCode() {
		return String.valueOf(value);
	}
}
