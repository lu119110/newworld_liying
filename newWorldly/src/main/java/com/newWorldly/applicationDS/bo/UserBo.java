package com.newWorldly.applicationDS.bo;

import java.util.List;

public class UserBo {
	String userName;
	String Pwd;
	String role;
	List menuList;
	List urlList;
	public List getUrlList() {
		return urlList;
	}
	public void setUrlList(List urlList) {
		this.urlList = urlList;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return Pwd;
	}
	public void setPwd(String pwd) {
		Pwd = pwd;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List getMenuList() {
		return menuList;
	}
	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}
	
	
}
