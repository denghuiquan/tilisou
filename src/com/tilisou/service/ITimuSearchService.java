package com.tilisou.service;

import com.tilisou.beans.Timu;
import com.tilisou.viewmodel.QueryResult;

/**
 * Timu题目检索数据服务接口
 * 
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-30 上午11:07:12
 * 
 */
public interface ITimuSearchService {
	/**
	 * 搜索题目
	 * 
	 * @param keyword
	 *            关键字
	 * @param firstResult
	 *            开始索引
	 * @param maxResult
	 *            每页获取的记录数
	 * @return
	 */
	public QueryResult<Timu> query(String keyword, int firstResult,
			int maxResult);

	/**
	 * 搜索匹配的题目
	 * 
	 * @param qr
	 *            检索结果
	 * @param firstResult
	 *            开始索引
	 * @param maxResult
	 *            每页获取的记录数
	 * @return
	 */
	public QueryResult<Timu> query(QueryResult<Timu> qr, int firstResult,
			int maxResult);
}
