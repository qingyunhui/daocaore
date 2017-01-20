package com.tw.service.sys;

import com.tw.dao.base.BaseDAO;
import com.tw.entity.sys.UserMenuRel;

public interface UserMenuRelService extends BaseDAO<UserMenuRel>{

	public void deleteByUserId(Integer id);
}
