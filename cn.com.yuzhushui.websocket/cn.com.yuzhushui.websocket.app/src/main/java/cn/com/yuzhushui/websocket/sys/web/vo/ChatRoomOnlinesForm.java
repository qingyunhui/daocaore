package cn.com.yuzhushui.websocket.sys.web.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import cn.com.yuzhushui.websocket.common.base.BaseForm;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-01-24 14:25:29
 * @history
 */
@Getter
@Setter
public class ChatRoomOnlinesForm extends BaseForm<Integer> {

	//columns START
	
	/**
	 * @Fields id:主键id
	 */
	private Integer id;
	
	/**
	 * @Fields chat_room_id:聊天室id
	 */
	private Integer chatRoomId;
	
	/**
	 * @Fields account_id:用户id(关联sys_account表)
	 */
	private Integer accountId;
	
	/**
	 * @Fields online_status:在线状态
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
	 * @Fields creater_id:创建人ID
	 */
	private Integer createrId;
	
	/**
	 * @Fields comments:备注
	 */
	private String comments;
	//columns END
}