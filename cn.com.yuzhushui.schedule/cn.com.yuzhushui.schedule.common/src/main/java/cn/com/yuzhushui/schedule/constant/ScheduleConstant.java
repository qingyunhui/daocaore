package cn.com.yuzhushui.schedule.constant;
/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月22日下午10:19:28
 **/
public class ScheduleConstant {
	
	/**session标识*/
	public static final String SESSION_INFO="SessionInfo";

	/**消息*/
	public static final String MESSAGES_INFO="messages";
	
	/**登陆次数标识*/
	public static final String  LOGON_COUNT_INFO="logonCount";
	
	/**登陆次数**/
	public static final int LOGON_COUNTS=3;
	
	/**锁定时间（单位:分钟）*/
	public static final int LOCK_TIME=30;
	
	/**cookie有效时间（2小时）*/
	public static final int COOKIE_VALIDITY_TIME=2*60*60;
	
	/**项目的顶级域名(子域名，二级域名都可以访问到，比如:abc.smiles8.com,top.smiles8.com)**/
	public static final String DOMAIN="smiles8.com";
	
	/**项目名称*/
	public static final String PROJECT_NAME="MOVIE";
	
	/**连接符*/
	public static final String JOINT="&=yzs=?";
	
	/**根目录*/
	public static final String ROOT_PATH = "/";
	
}
