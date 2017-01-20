package com.tw.service.sys.impl;

import org.springframework.stereotype.Service;

import com.tw.dao.base.BaseDAOSupport;
import com.tw.entity.sys.UserRoleRel;
import com.tw.service.sys.UserRoleRelService;
@Service("userRoleRelService")
public class UserRoleRelServiceImpl extends BaseDAOSupport<UserRoleRel> implements UserRoleRelService{

	public void deleteByUserId(Integer id) {
		em.createQuery("delete from UserRoleRel o where o.id.userId=?1").setParameter(1, id).executeUpdate();
	}
}
