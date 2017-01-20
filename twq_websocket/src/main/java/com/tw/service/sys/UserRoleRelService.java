package com.tw.service.sys;

import com.tw.dao.base.BaseDAO;
import com.tw.entity.sys.UserRoleRel;

public interface UserRoleRelService extends BaseDAO<UserRoleRel>{

	public void deleteByUserId(Integer id);
}
