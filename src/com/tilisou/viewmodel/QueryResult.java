package com.tilisou.viewmodel;

import java.util.List;

/**
 * ��ѯ���������ʵ���б���
 * 
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-18 ����8:34:54
 * 
 * @param <T>
 *            ����ʵ�壬ָ�����صĽ������
 */
public class QueryResult<T> {
	/** ����б�  **/
	private List<T> resultlist;
	/** ��¼����  **/
	private long totalrecord;
	
	public List<T> getResultlist() {
		return resultlist;
	}
	public void setResultlist(List<T> resultlist) {
		this.resultlist = resultlist;
	}
	public long getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
	}
}
