package cn.com.yuzhushui.schedule.common.bean;

import cn.com.yuzhushui.schedule.sys.biz.entity.SysAccount;
import cn.com.yuzhushui.schedule.sys.biz.entity.SysUser;
import lombok.Data;

/***
 ** @Description: 请用一句话来描述
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Oct 27, 2015 9:23:57 AM
 ** @version: V1.0
 ***/
@Data
public class SessionInfo {
	private SysUser sysUser;
	private SysAccount sysAccount;
}
