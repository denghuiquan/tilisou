package com.tilisou.viewmodel;

import java.util.List;

/**
 * 查询或检索到的实体列表结果
 * 
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-18 下午8:34:54
 * 
 * @param <T>
 *            泛型实体，指代返回的结果类型
 */
public class QueryResult<T> {
	/** 结果列表  **/
	private List<T> resultlist;
	/** 记录总数  **/
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
