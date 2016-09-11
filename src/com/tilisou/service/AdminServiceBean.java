package com.tilisou.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tilisou.beans.Admin;
import com.tilisou.utils.MD5;

/**
 * 管理员实体数据服务实现类
 * 
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-30 下午3:00:32
 * 
 */
@Service
@Transactional
public class AdminServiceBean extends DaoSupport<Admin> implements
		IAdminService {

	public void updatePassword(String username, String newpassword) {
		em.createQuery("update Admin o set o.password=?1 where o.name=?2")
				.setParameter(1, newpassword).setParameter(2, username)
				.executeUpdate();
	}

	public void save(Admin entity) {
		entity.setPassword(MD5.MD5Encode(entity.getPassword()));
		super.save(entity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Admin find(Serializable username) {
		List<Admin> admins=em.createQuery("select o from Admin o where o.name=?1").setParameter(1, username).getResultList();
		return (Admin) admins.get(0);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public boolean validate(String username, String password) {
		long count = (Long) em
				.createQuery(
						"select count(o) from Admin o where o.name=?1 and o.password=?2")
				.setParameter(1, username)
				.setParameter(2, /*MD5.MD5Encode()*/password).getSingleResult();
		return count > 0;
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public long getCount() {
		return (Long) em.createQuery("select count(o) from Admin o").getSingleResult();
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public boolean exsit(String name) {
		long count = (Long) em
				.createQuery("select count(o) from Admin o where o.name=?1")
				.setParameter(1, name).getSingleResult();
		return count > 0;
	}
}
