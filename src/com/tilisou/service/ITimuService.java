package com.tilisou.service;

import java.util.List;

import com.tilisou.beans.Timu;


/**
 * Timu��Ŀʵ��Ļ������ݷ���ӿ�
 * 
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-30 ����11:02:03
 * 
 */
public interface ITimuService extends IDao<Timu> {

	/**
	 * ��ȡ�꼶�µ���Ŀ
	 * 
	 * @param grade
	 *            �꼶
	 * @return
	 */
	public List<Timu> getTimusByGrade(String grade);

	/**
	 * ��ȡ�����µ���Ŀ
	 * 
	 * @param type
	 *            ����
	 * @return
	 */
	public List<Timu> getTimusByType(String type);

	/**
	 * ��ȡ��Ŀ�µ���Ŀ
	 * 
	 * @param subject
	 *            ��Ŀ
	 * @return
	 */
	public List<Timu> getTimusBySubject(String subject);

	/**
	 * ��ȡ�������ʷ��¼
	 * 
	 * @param tids
	 *            ��Ŀid����
	 * @param maxResult
	 *            ����ȡ��������¼
	 * @return
	 */
	public List<Timu> getViewHistory(Integer[] tids, int maxResult);
}
