package com.tilisou.service;

import com.tilisou.beans.Admin;

/**
 * 管理员数据服务接口
 * 
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-30 下午3:01:10
 * 
 */
public interface IAdminService extends IDao<Admin> {

	/**
	 * 判断用户是否存在
	 * 
	 * @param name
	 *            用户名
	 * @return
	 */
	public boolean exsit(String name);

	/**
	 * 更新密码
	 * 
	 * @param name
	 *            用户名
	 * @param newpassword
	 *            新密码
	 */
	public void updatePassword(String name, String newpassword);

	/**
	 * 判断用户名及密码是否正确
	 * 
	 * @param name
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	public boolean validate(String name, String password);
}