package cn.com.daocaore.mongodb.sys.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import cn.com.daocaore.mongodb.common.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-23 00:05:03
 * @history
 */
@Getter
@Setter
@Document(collection="sys_account")
public class SysAccount extends BaseModel<String> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * @Fields accountId:account_id
	 */
	@Id
//	@Field(value="account_id")
	private String accountId;
	
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
	@Field(value="creater_id")
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
	@Field(value="editor_id")
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
	
	@Transient	//添加Transient 注解的字段，是不会将其添加到数据库中的...	
	private String test;

	@Override
	public String getId() {
		return accountId;
	}

}