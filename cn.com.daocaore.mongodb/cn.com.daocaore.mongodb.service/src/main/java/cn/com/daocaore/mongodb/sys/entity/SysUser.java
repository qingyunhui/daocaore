package cn.com.daocaore.mongodb.sys.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import cn.com.daocaore.mongodb.common.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-23 00:05:04
 * @history
 */
@SuppressWarnings("serial")
@Getter
@Setter
@Document(collection="sys_user")
public class SysUser extends BaseModel<String> implements Serializable{
	
	//columns START
	/**
	 * @Fields userId:id
	 */
	@Id
//	@Field(value="user_id")		
	private String userId;
	
	//@Id 标识的字段会作为主键，并且其类型必须是String，如果一个字段在标注@Id后，又指定了数据表字段@Field那么始终会以mongoDB默认的_id为准，所以在加上@Id后，就没有必要添加@Field了..
	
	/**
	 * @Fields accountId:account_id
	 */
	@Field(value="account_id")
	private Integer accountId;
	
	/**
	 * @Fields name:姓名
	 */
	private String name;
	
	/**
	 * @Fields sex:性别(0.男、1.女、2.其它)
	 */
	private Integer sex;
	
	/**
	 * @Fields age:年龄
	 */
	private Integer age;
	
	/**
	 * @Fields telephone:联系电话
	 */
	private String telephone;
	
	/**
	 * @Fields mobilephone:移动电话
	 */
	private String mobilephone;
	
	/**
	 * @Fields job:职务(1.IT、2.农业、3.其它)
	 */
	private Integer job;
	
	/**
	 * @Fields qq:QQ
	 */
	private Integer qq;
	
	/**
	 * @Fields wechat:微信
	 */
	private String wechat;
	
	/**
	 * @Fields microblog:微博
	 */
	private String microblog;
	
	/**
	 * @Fields email:电子邮箱
	 */
	private String email;
	
	/**
	 * @Fields officeAddr:办公地址
	 */
	@Field(value="office_addr")
	private String officeAddr;
	
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
	 * @Fields editorId:修改人_id
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
	
	@Transient
	private String test;

	@Override
	public String getId() {
		return userId;
	}

}