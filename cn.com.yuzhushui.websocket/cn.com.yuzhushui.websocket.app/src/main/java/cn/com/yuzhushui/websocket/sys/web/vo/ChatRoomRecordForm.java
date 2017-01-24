package cn.com.yuzhushui.websocket.sys.web.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import cn.com.yuzhushui.websocket.common.base.BaseForm;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-01-24 14:25:28
 * @history
 */
@Getter
@Setter
public class ChatRoomRecordForm extends BaseForm<Integer> {

	//columns START
	
	/**
	 * @Fields id:主键id
	 */
	private Integer id;
	
	/**
	 * @Fields chat_room_id:聊天室id(关联chat_room表)
	 */
	private Integer chatRoomId;
	
	/**
	 * @Fields account_id:账号id(关联sys_account表)
	 */
	private Integer accountId;
	
	/**
	 * @Fields msg_body:消息内容
	 */
	private String msgBody;
	
	/**
	 * @Fields msg_type:消息类型
	 */
	private Integer msgType;
	
	/**
	 * @Fields receive_time:服务器接收消息的时间
	 */
	private Date receiveTime;
	
	/**
	 * @Fields ctime:创建时间
	 */
	private Date ctime;
	
	/**
	 * @Fields creater:创建人
	 */
	private String creater;
	
	/**
	 * @Fields creater_id:创建人ID
	 */
	private Integer createrId;
	
	/**
	 * @Fields comments:备注
	 */
	private String comments;
	//columns END
}