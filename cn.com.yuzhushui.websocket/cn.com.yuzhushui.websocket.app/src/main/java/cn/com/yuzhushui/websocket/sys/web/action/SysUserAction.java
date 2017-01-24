package cn.com.yuzhushui.websocket.sys.web.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yuzhushui.websocket.chat.biz.entity.SysUser;
import cn.com.yuzhushui.websocket.common.base.BaseAction;
import cn.com.yuzhushui.websocket.sys.web.vo.SysUserForm;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-01-24 14:46:41
 * @history
 */
@Controller
@RequestMapping(SysUserAction.ACTION_PATH)
public class SysUserAction extends BaseAction<SysUser, SysUserForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(SysUserAction.class);
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysUser";
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}