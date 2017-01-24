package cn.com.yuzhushui.websocket.common.bean;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 用户登录、注册时的信息
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月22日下午10:47:22
 **/

@Getter
@Setter
public class LogParameter {
	
	private String accounts;//	账号or手机号or邮箱、登陆、注册
	
	private String passwords;//  密码
	
	private String confirmPasswords;//确认密码
	
	private String verifCodes;//验证码
	
	private String options;//选项(标识是账号、手机号、邮箱进行注册或者登陆)
	
}
