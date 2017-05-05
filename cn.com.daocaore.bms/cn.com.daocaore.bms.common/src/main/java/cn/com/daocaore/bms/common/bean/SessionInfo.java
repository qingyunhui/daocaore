package cn.com.daocaore.bms.common.bean;

import lombok.Data;
import cn.com.daocaore.bms.sys.biz.entity.SysAccount;
import cn.com.daocaore.bms.sys.biz.entity.SysUser;

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
