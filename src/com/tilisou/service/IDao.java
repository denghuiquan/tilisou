package com.tilisou.service;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.tilisou.viewmodel.QueryResult;

/**
 * ���ݿ���������ӿ�
 * 
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-30 ����10:05:11
 * @param <T>
 *            ����֧�֣�������ʵ������ <br/>
 * �ṩ�������CRUD����
 */
public interface IDao<T> {
	/**
	 * ��ȡ��¼����
	 * 
	 * @param entityClass
	 *            ʵ����
	 * @return long
	 */
	public long getCount();

	/**
	 * ���һ�����������
	 */
	public void clear();

	/**
	 * ����ʵ��
	 * 
	 * @param entity
	 *            ʵ��id
	 */
	public void save(T entity);

	/**
	 * ����ʵ��
	 * 
	 * @param entity
	 *            ʵ��id
	 */
	public void update(T entity);

	/**
	 * ɾ��ʵ��
	 * 
	 * @param entityClass
	 *            ʵ����
	 * @param entityids
	 *            ʵ��id����
	 */
	public void delete(Serializable... entityids);

	/**
	 * ��ȡʵ��
	 * 
	 * @param <T>
	 * @param entityClass
	 *            ʵ����
	 * @param entityId
	 *            ʵ��id
	 * @return <T>
	 */
	public T find(Serializable entityId);

	/**
	 * ��ȡ��ҳ����
	 * 
	 * @param <T>
	 * @param entityClass
	 *            ʵ����
	 * @param firstindex
	 *            ��ʼ����
	 * @param maxresult
	 *            ��Ҫ��ȡ�ļ�¼��
	 * @param wherejpql
	 *            ��ѯ�������
	 * @param queryParams
	 *            ��ѯ������������
	 * @param orderby
	 *            �������Ժͷ�ʽ��ֵ��
	 * @return QueryResult<T>
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby);

	/**
	 * ��ȡ��ҳ����
	 * 
	 * @param <T>
	 * @param entityClass
	 *            ʵ����
	 * @param firstindex
	 *            ��ʼ����
	 * @param maxresult
	 *            ��Ҫ��ȡ�ļ�¼��
	 * @param wherejpql
	 *            ��ѯ�������
	 * @param queryParams
	 *            ��ѯ������������
	 * @return QueryResult<T>
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams);

	/**
	 * ��ȡ��ҳ����
	 * 
	 * @param <T>
	 * @param entityClass
	 *            ʵ����
	 * @param firstindex
	 *            ��ʼ����
	 * @param maxresult
	 *            ��Ҫ��ȡ�ļ�¼��
	 * @param orderby
	 *            �������Ժͷ�ʽ��ֵ��
	 * @return QueryResult<T>
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult,
			LinkedHashMap<String, String> orderby);

	/**
	 * ��ȡ��ҳ����
	 * 
	 * @param <T>
	 * @param entityClass
	 *            ʵ����
	 * @param firstindex
	 *            ��ʼ����
	 * @param maxresult
	 *            ��Ҫ��ȡ�ļ�¼��
	 * @return QueryResult<T>
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult);

	/**
	 * ��ȡ��ҳ����
	 * 
	 * @param <T>
	 * @param entityClass
	 *            ʵ����
	 * @return QueryResult<T>
	 */
	public QueryResult<T> getScrollData();
}
