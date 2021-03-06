package cn.com.yuzhushui.websocket.chat.biz.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import cn.com.yuzhushui.websocket.common.base.BaseModel;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-01-24 14:25:28
 * @history
 */
@Getter
@Setter
public class ChatRoomRecord extends BaseModel<Integer>{
	
	//alias
	public static final String TABLE_ALIAS = "ChatRoomRecord";
	
	//columns START
	/**
	 * @Fields id:主键id
	 */
	private Integer id;
	
	/**
	 * @Fields chatRoomId:聊天室id(关联chat_room表)
	 */
	private Integer chatRoomId;
	
	/**
	 * @Fields accountId:账号id(关联sys_account表)
	 */
	private Integer accountId;
	
	/**
	 * @Fields msgBody:消息内容
	 */
	private String msgBody;
	
	/**
	 * @Fields msgType:消息类型
	 */
	private Integer msgType;
	
	/**
	 * @Fields receiveTime:服务器接收消息的时间
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
	 * @Fields createrId:创建人ID
	 */
	private Integer createrId;
	
	/**
	 * @Fields comments:备注
	 */
	private String comments;
	
	/**
	 * @Fields deleted:删除标识
	 */
	private Integer deleted;
	
	//columns END

}