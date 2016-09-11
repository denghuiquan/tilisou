package com.tilisou.service;

import com.tilisou.beans.Timu;
import com.tilisou.viewmodel.QueryResult;

/**
 * Timu��Ŀ�������ݷ���ӿ�
 * 
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-30 ����11:07:12
 * 
 */
public interface ITimuSearchService {
	/**
	 * ������Ŀ
	 * 
	 * @param keyword
	 *            �ؼ���
	 * @param firstResult
	 *            ��ʼ����
	 * @param maxResult
	 *            ÿҳ��ȡ�ļ�¼��
	 * @return
	 */
	public QueryResult<Timu> query(String keyword, int firstResult,
			int maxResult);

	/**
	 * ����ƥ�����Ŀ
	 * 
	 * @param qr
	 *            �������
	 * @param firstResult
	 *            ��ʼ����
	 * @param maxResult
	 *            ÿҳ��ȡ�ļ�¼��
	 * @return
	 */
	public QueryResult<Timu> query(QueryResult<Timu> qr, int firstResult,
			int maxResult);
}
