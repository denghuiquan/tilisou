package com.tilisou.service;

import java.util.List;

import com.tilisou.beans.Timu;


/**
 * Timu题目实体的基本数据服务接口
 * 
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-30 上午11:02:03
 * 
 */
public interface ITimuService extends IDao<Timu> {

	/**
	 * 获取年级下的题目
	 * 
	 * @param grade
	 *            年级
	 * @return
	 */
	public List<Timu> getTimusByGrade(String grade);

	/**
	 * 获取类型下的题目
	 * 
	 * @param type
	 *            类型
	 * @return
	 */
	public List<Timu> getTimusByType(String type);

	/**
	 * 获取科目下的题目
	 * 
	 * @param subject
	 *            科目
	 * @return
	 */
	public List<Timu> getTimusBySubject(String subject);

	/**
	 * 获取浏览的历史记录
	 * 
	 * @param tids
	 *            题目id数组
	 * @param maxResult
	 *            最大获取多少条记录
	 * @return
	 */
	public List<Timu> getViewHistory(Integer[] tids, int maxResult);
}
