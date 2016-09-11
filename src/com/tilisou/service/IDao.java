package com.tilisou.service;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.tilisou.viewmodel.QueryResult;

/**
 * 数据库操作公共接口
 * 
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-30 上午10:05:11
 * @param <T>
 *            泛型支持，操作的实体类型 <br/>
 * 提供最基本的CRUD操作
 */
public interface IDao<T> {
	/**
	 * 获取记录总数
	 * 
	 * @param entityClass
	 *            实体类
	 * @return long
	 */
	public long getCount();

	/**
	 * 清除一级缓存的数据
	 */
	public void clear();

	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            实体id
	 */
	public void save(T entity);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 *            实体id
	 */
	public void update(T entity);

	/**
	 * 删除实体
	 * 
	 * @param entityClass
	 *            实体类
	 * @param entityids
	 *            实体id数组
	 */
	public void delete(Serializable... entityids);

	/**
	 * 获取实体
	 * 
	 * @param <T>
	 * @param entityClass
	 *            实体类
	 * @param entityId
	 *            实体id
	 * @return <T>
	 */
	public T find(Serializable entityId);

	/**
	 * 获取分页数据
	 * 
	 * @param <T>
	 * @param entityClass
	 *            实体类
	 * @param firstindex
	 *            开始索引
	 * @param maxresult
	 *            需要获取的记录数
	 * @param wherejpql
	 *            查询条件语句
	 * @param queryParams
	 *            查询参数对象数据
	 * @param orderby
	 *            排序属性和方式键值对
	 * @return QueryResult<T>
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby);

	/**
	 * 获取分页数据
	 * 
	 * @param <T>
	 * @param entityClass
	 *            实体类
	 * @param firstindex
	 *            开始索引
	 * @param maxresult
	 *            需要获取的记录数
	 * @param wherejpql
	 *            查询条件语句
	 * @param queryParams
	 *            查询参数对象数据
	 * @return QueryResult<T>
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams);

	/**
	 * 获取分页数据
	 * 
	 * @param <T>
	 * @param entityClass
	 *            实体类
	 * @param firstindex
	 *            开始索引
	 * @param maxresult
	 *            需要获取的记录数
	 * @param orderby
	 *            排序属性和方式键值对
	 * @return QueryResult<T>
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult,
			LinkedHashMap<String, String> orderby);

	/**
	 * 获取分页数据
	 * 
	 * @param <T>
	 * @param entityClass
	 *            实体类
	 * @param firstindex
	 *            开始索引
	 * @param maxresult
	 *            需要获取的记录数
	 * @return QueryResult<T>
	 */
	public QueryResult<T> getScrollData(int firstindex, int maxresult);

	/**
	 * 获取分页数据
	 * 
	 * @param <T>
	 * @param entityClass
	 *            实体类
	 * @return QueryResult<T>
	 */
	public QueryResult<T> getScrollData();
}
