package com.tw.entity.sys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 用户
 * @author Administrator
 *
 */
@Entity
@Table(name = "TUSER")
@DynamicInsert(true)
@DynamicUpdate(true)
public class User implements Serializable{

	private static final long serialVersionUID = -8308409184230815480L;
	private Integer id;
	private String name;
	private String pwd;
	private String email;
	private String emailw;
	/**
	 * 1 生效 0 注销
	 */
	private String cancel = "1";
	private Date createDate = new Date();
	private String phone;
	private String mphone;
	private String realName;
	
	@Id @GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=20,nullable=false,unique=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length=50,nullable=false)
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Column(length=50,nullable=false,unique=true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(length=1,nullable=false)
	public String getCancel() {
		return cancel;
	}
	public void setCancel(String cancel) {
		this.cancel = cancel;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, length = 26)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(length=100,unique=true)
	public String getEmailw() {
		return emailw;
	}
	public void setEmailw(String emailw) {
		this.emailw = emailw;
	}
	@Column(length=50)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(length=12)
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	@Column(length=10)
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
}
