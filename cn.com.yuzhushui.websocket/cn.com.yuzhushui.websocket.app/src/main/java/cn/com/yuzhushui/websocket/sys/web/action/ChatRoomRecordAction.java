package cn.com.yuzhushui.websocket.sys.web.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yuzhushui.websocket.chat.biz.entity.ChatRoomRecord;
import cn.com.yuzhushui.websocket.common.base.BaseAction;
import cn.com.yuzhushui.websocket.sys.web.vo.ChatRoomRecordForm;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-01-24 14:25:28
 * @history
 */
@Controller
@RequestMapping(ChatRoomRecordAction.ACTION_PATH)
public class ChatRoomRecordAction extends BaseAction<ChatRoomRecord, ChatRoomRecordForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(ChatRoomRecordAction.class);
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/chat/chatRoomRecord";
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}