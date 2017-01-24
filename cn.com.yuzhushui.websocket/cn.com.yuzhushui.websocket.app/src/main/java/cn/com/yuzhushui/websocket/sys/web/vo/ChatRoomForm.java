package cn.com.yuzhushui.websocket.sys.web.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import cn.com.yuzhushui.websocket.common.base.BaseForm;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-01-24 14:25:27
 * @history
 */
@Getter
@Setter
public class ChatRoomForm extends BaseForm<Integer> {

	//columns START
	
	/**
	 * @Fields id:主键id
	 */
	private Integer id;
	
	/**
	 * @Fields account_id:群主id(关联sys_account表)这里考虑到群可以转让转让后群主也相应会转让
	 */
	private Integer accountId;
	
	/**
	 * @Fields name:群名称
	 */
	private String name;
	
	/**
	 * @Fields intro:群简介
	 */
	private String intro;
	
	/**
	 * @Fields status:状态(0.未审核、1.审核不通过、2.审核通过、3.停用)
	 */
	private Integer status;
	
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
	 * @Fields etime:修改时间
	 */
	private Date etime;
	
	/**
	 * @Fields editor:修改人
	 */
	private String editor;
	
	/**
	 * @Fields editor_id:修改人ID
	 */
	private Integer editorId;
	
	/**
	 * @Fields comments:备注
	 */
	private String comments;
	//columns END
}