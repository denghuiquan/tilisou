package com.tilisou.service;

import javax.annotation.Resource;
import org.compass.core.Compass;
import org.compass.core.CompassTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tilisou.beans.Timu;
import com.tilisou.viewmodel.QueryResult;

/**
 * Timu题目检索数据服务实现类
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-12 上午11:09:08
 * 
 */
@Service
@Transactional
public class TimuSearchServiceBean extends DaoSupport<Timu> implements
		ITimuSearchService {
	/*
	 * @Resource private ITimuService timuService;
	 */
	private CompassTemplate compassTemplate;

	@Resource
	public void setCompass(Compass compass) {
		this.compassTemplate = new CompassTemplate(compass);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public QueryResult<Timu> query(String keyword, int firstResult,
			int maxResult) {
		//需要在网页处对关键词进行检查，避免空词的查询操作，返回空结果
		return compassTemplate.execute(new QueryCallback(keyword, firstResult,
				maxResult));
	}

	public QueryResult<Timu> query(QueryResult<Timu> qr, int firstResult,
			int maxResult) {
		/*List<Timu> timus = new ArrayList<Timu>();
		List<Timu> tis = new ArrayList<Timu>();
		List<Timu> realTimus = new ArrayList<Timu>();
		List<Timu> list = qr.getResultlist();
		Integer ids[] = new Integer[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ids[i] = list.get(i).gettId();
		}
		timus = timuService.getScrollData().getResultlist();
		if (timus != null && list.size() > timus.size()) {
			for (int i = 0; i < timus.size(); i++) {
				realTimus.add(list.get(i));
			}
		} else {
			for (int i = 0; i < list.size(); i++) {
				realTimus.add(list.get(i));
			}
		}

		int length = firstResult + maxResult;
		if (length > realTimus.size())
			length = realTimus.size();
		qr.setTotalrecord(realTimus.size());// 获取匹配记录的总数
		for (int i = firstResult; i < length; i++) {
			tis.add(realTimus.get(i));
		}
		qr.setResultlist(tis);*/
		return qr;
	}

}
