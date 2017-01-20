package com.tw.service.sys.impl;

import org.springframework.stereotype.Service;

import com.tw.dao.base.BaseDAOSupport;
import com.tw.entity.sys.UserMenuRel;
import com.tw.service.sys.UserMenuRelService;
@Service("userMenuRelService")
public class UserMenuRelServiceImpl extends BaseDAOSupport<UserMenuRel> implements UserMenuRelService{

	public void deleteByUserId(Integer id) {
		em.createQuery("delete from UserMenuRel o where o.id.userId=?1").setParameter(1, id).executeUpdate();
	}
}
