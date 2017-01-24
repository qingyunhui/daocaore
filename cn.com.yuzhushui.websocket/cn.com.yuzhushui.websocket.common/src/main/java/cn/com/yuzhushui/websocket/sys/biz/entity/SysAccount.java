package cn.com.yuzhushui.websocket.sys.biz.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import cn.com.yuzhushui.websocket.common.base.BaseModel;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-23 00:05:03
 * @history
 */
@Getter
@Setter
public class SysAccount extends BaseModel<Integer>{
	
	//alias
	public static final String TABLE_ALIAS = "SysAccount";
	
	//columns START
	/**
	 * @Fields accountId:account_id
	 */
	private Integer accountId;
	
	/**
	 * @Fields account:账号
	 */
	private String account;
	
	/**
	 * @Fields mobilephone:移动电话
	 */
	private String mobilephone;
	
	/**
	 * @Fields email:电子邮箱
	 */
	private String email;
	
	/**
	 * @Fields password:密码(加密后)
	 */
	private String password;
	
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

	@Override
	public Integer getId() {
		return accountId;
	}
	
	//columns END

}