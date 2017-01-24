package cn.com.yuzhushui.websocket.sys.web.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import cn.com.yuzhushui.websocket.common.base.BaseForm;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-01-24 14:46:42
 * @history
 */
@Getter
@Setter
public class SysUserFriendForm extends BaseForm<Integer> {

	//columns START
	
	/**
	 * @Fields id:id
	 */
	private Integer id;
	
	/**
	 * @Fields account_id:account_id
	 */
	private Integer accountId;
	
	/**
	 * @Fields source_account_id:发起者的id(关联sys_account表)
	 */
	private Integer sourceAccountId;
	
	/**
	 * @Fields target_account_id:目标用户的id(关联sys_account表)
	 */
	private String targetAccountId;
	
	/**
	 * @Fields status:状态(0:已发送，1.已拒绝，2.已同意，3.已拉黑，4.已删除)
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