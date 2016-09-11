package com.tilisou.viewmodel;

import com.tilisou.utils.MD5;

/**
 * 管理员FormBean
 * 
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-8 下午10:35:01
 * 
 */
public class AdminForm extends BaseForm {
	
	private static final long serialVersionUID = 3116932710143896603L;
	private String managername;
	private String password;
	private String email;


	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = MD5.MD5Encode(password);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
