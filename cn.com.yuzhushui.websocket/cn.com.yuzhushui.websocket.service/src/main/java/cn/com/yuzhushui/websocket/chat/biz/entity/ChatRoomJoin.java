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
public class ChatRoomJoin extends BaseModel<Integer>{
	
	//alias
	public static final String TABLE_ALIAS = "ChatRoomJoin";
	
	//columns START
	/**
	 * @Fields id:id
	 */
	private Integer id;
	
	/**
	 * @Fields classify:聊天室分类(同学、亲友、聚会)
	 */
	private Integer classify;
	
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
	 * @Fields etime:修改时间
	 */
	private Date etime;
	
	/**
	 * @Fields editor:修改人
	 */
	private String editor;
	
	/**
	 * @Fields editorId:修改人ID
	 */
	private Integer editorId;
	
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