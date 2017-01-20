package com.tw.vo.sys;

import java.util.List;

public class UserVo {

	private Integer id;
	private String name;
	private String pwd;
	private String email;
	private String emailw;
	private String cancel;
	private String safecode;
	
	private int page;
	private int rows;
	
	private List<String> url;
	private String invite;
	private String phone;
	private String mphone;
	private String realName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCancel() {
		return cancel;
	}
	public void setCancel(String cancel) {
		this.cancel = cancel;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public List<String> getUrl() {
		return url;
	}
	public void setUrl(List<String> url) {
		this.url = url;
	}
	public String getSafecode() {
		return safecode;
	}
	public void setSafecode(String safecode) {
		this.safecode = safecode;
	}
	public String getInvite() {
		return invite;
	}
	public void setInvite(String invite) {
		this.invite = invite;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getEmailw() {
		return emailw;
	}
	public void setEmailw(String emailw) {
		this.emailw = emailw;
	}
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", pwd=" + pwd
				+ ", email=" + email + ", emailw=" + emailw + ", cancel="
				+ cancel + ", safecode=" + safecode + ", page=" + page
				+ ", rows=" + rows + ", url=" + url + ", invite=" + invite
				+ ", phone=" + phone + ", mphone=" + mphone + ", realName="
				+ realName + "]";
	}
}
