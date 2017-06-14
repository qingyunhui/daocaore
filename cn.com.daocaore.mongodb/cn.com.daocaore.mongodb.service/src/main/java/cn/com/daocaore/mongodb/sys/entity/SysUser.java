package cn.com.daocaore.mongodb.sys.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-23 00:05:04
 * @history
 */
/*@Getter
@Setter*/
@Document(collection="sys_user")
public class SysUser implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//columns START
	/**
	 * @Fields userId:id
	 */
	@Id
	private String userId;
	
	/**
	 * @Fields accountId:account_id
	 */
	private String accountId;
	
	/**
	 * @Fields name:姓名
	 */
	private String name;
	
	/**
	 * @Fields sex:性别(0.男、1.女、2.其它)
	 */
	private String sex;
	
	/**
	 * @Fields age:年龄
	 */
	private String age;
	
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
	private String job;
	
	/**
	 * @Fields qq:QQ
	 */
	private String qq;
	
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
	private String createrId;
	
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
	private String editorId;
	
	/**
	 * @Fields comments:备注
	 */
	private String comments;
	
	/**
	 * @Fields deleted:删除标识
	 */
	private String deleted;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getMicroblog() {
		return microblog;
	}

	public void setMicroblog(String microblog) {
		this.microblog = microblog;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOfficeAddr() {
		return officeAddr;
	}

	public void setOfficeAddr(String officeAddr) {
		this.officeAddr = officeAddr;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}


	public Date getEtime() {
		return etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}


	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public String getEditorId() {
		return editorId;
	}

	public void setEditorId(String editorId) {
		this.editorId = editorId;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	//columns END
	
	/*@Transient
	private String test;*/

}