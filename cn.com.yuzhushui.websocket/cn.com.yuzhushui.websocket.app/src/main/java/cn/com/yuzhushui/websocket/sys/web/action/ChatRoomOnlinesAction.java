package cn.com.yuzhushui.websocket.sys.web.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yuzhushui.websocket.chat.biz.entity.ChatRoomOnlines;
import cn.com.yuzhushui.websocket.common.base.BaseAction;
import cn.com.yuzhushui.websocket.sys.web.vo.ChatRoomOnlinesForm;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-01-24 14:25:29
 * @history
 */
@Controller
@RequestMapping(ChatRoomOnlinesAction.ACTION_PATH)
public class ChatRoomOnlinesAction extends BaseAction<ChatRoomOnlines, ChatRoomOnlinesForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(ChatRoomOnlinesAction.class);
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/chat/chatRoomOnlines";
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}