package com.tw.service.sys.impl;

import org.springframework.stereotype.Service;

import com.tw.dao.base.BaseDAOSupport;
import com.tw.entity.sys.Roles;
import com.tw.service.sys.RolesService;
@Service("rolesService")
public class RolesServiceImpl extends BaseDAOSupport<Roles> implements RolesService{

}
