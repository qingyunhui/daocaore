package cn.com.yuzhushui.websocket.sys.web.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yuzhushui.websocket.chat.biz.entity.ChatRoom;
import cn.com.yuzhushui.websocket.common.base.BaseAction;
import cn.com.yuzhushui.websocket.sys.web.vo.ChatRoomForm;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-01-24 14:25:27
 * @history
 */
@Controller
@RequestMapping(ChatRoomAction.ACTION_PATH)
public class ChatRoomAction extends BaseAction<ChatRoom, ChatRoomForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(ChatRoomAction.class);
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/chat/chatRoom";
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}