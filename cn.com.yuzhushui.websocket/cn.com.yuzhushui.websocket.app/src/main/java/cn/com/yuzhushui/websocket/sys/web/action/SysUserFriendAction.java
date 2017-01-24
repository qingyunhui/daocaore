package cn.com.yuzhushui.websocket.sys.web.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yuzhushui.websocket.chat.biz.entity.SysUserFriend;
import cn.com.yuzhushui.websocket.common.base.BaseAction;
import cn.com.yuzhushui.websocket.sys.web.vo.SysUserFriendForm;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-01-24 14:46:42
 * @history
 */
@Controller
@RequestMapping(SysUserFriendAction.ACTION_PATH)
public class SysUserFriendAction extends BaseAction<SysUserFriend, SysUserFriendForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(SysUserFriendAction.class);
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysUserFriend";
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}