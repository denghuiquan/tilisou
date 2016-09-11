package com.tilisou.service;

import com.tilisou.beans.Admin;

/**
 * ����Ա���ݷ���ӿ�
 * 
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-30 ����3:01:10
 * 
 */
public interface IAdminService extends IDao<Admin> {

	/**
	 * �ж��û��Ƿ����
	 * 
	 * @param name
	 *            �û���
	 * @return
	 */
	public boolean exsit(String name);

	/**
	 * ��������
	 * 
	 * @param name
	 *            �û���
	 * @param newpassword
	 *            ������
	 */
	public void updatePassword(String name, String newpassword);

	/**
	 * �ж��û����������Ƿ���ȷ
	 * 
	 * @param name
	 *            �û���
	 * @param password
	 *            ����
	 * @return
	 */
	public boolean validate(String name, String password);
}