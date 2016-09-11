package com.tilisou.service;

import java.util.List;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tilisou.beans.Timu;

/**
 * Timu��Ŀʵ�����ݷ������ʵ����
 * 
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-30 ����11:11:51
 * 
 */
@Service
@Transactional
public class TimuServiceBean extends DaoSupport<Timu> implements ITimuService {

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Timu> getViewHistory(Integer[] tids, int maxResult) {
		StringBuffer jpql = new StringBuffer();
		// ƴ����ʷ�鿴��Ŀ��Id��
		for (int i = 0; i < tids.length; i++) {
			jpql.append('?').append(i + 1).append(',');
		}
		jpql.deleteCharAt(jpql.length() - 1);
		// ������ѯ
		Query query = em.createQuery("select o from Timu o where o.id in("
				+ jpql.toString() + ")");
		// ����ѯ�е�ռλ�����ò���ֵ
		for (int i = 0; i < tids.length; i++) {
			query.setParameter(i + 1, tids[i]);
		}
		// ���ò�ѯ����ʼ����Ͳ�ѯָ���������
		query.setFirstResult(0).setMaxResults(maxResult);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Timu> getTimusByGrade(String grade) {
		if (grade != null && "".equals(grade)) {
			Query query = em
					.createQuery("select t from Timu t where t.grade=?1");

			query.setParameter(1, grade);
			return query.getResultList();
		}
		return null;
	}

	@Override
	public void save(Timu entity) {
		super.save(entity);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Timu> getTimusByType(String type) {
		if (type != null && "".equals(type)) {
			Query query = em
					.createQuery("select t from Timu t where t.type=?1");

			query.setParameter(1, type);
			return query.getResultList();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Timu> getTimusBySubject(String subject) {
		if (subject != null && "".equals(subject)) {
			Query query = em
					.createQuery("select t from Timu t where t.subject=?1");

			query.setParameter(1, subject);
			return query.getResultList();
		}
		return null;
	}

}
