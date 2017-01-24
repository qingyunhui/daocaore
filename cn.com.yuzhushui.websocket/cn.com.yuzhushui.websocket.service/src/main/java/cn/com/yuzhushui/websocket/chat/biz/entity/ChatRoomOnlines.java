package cn.com.yuzhushui.websocket.chat.biz.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import cn.com.yuzhushui.websocket.common.base.BaseModel;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-01-24 14:25:29
 * @history
 */
@Getter
@Setter
public class ChatRoomOnlines extends BaseModel<Integer>{
	
	//alias
	public static final String TABLE_ALIAS = "ChatRoomOnlines";
	
	//columns START
	/**
	 * @Fields id:主键id
	 */
	private Integer id;
	
	/**
	 * @Fields chatRoomId:聊天室id
	 */
	private Integer chatRoomId;
	
	/**
	 * @Fields accountId:用户id(关联sys_account表)
	 */
	private Integer accountId;
	
	/**
	 * @Fields onlineStatus:在线状态
	 */
	private Integer onlineStatus;
	
	/**
	 * @Fields token:每进入一次都会生成一个token
	 */
	private String token;
	
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