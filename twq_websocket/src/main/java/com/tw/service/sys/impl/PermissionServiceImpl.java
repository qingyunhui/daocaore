package com.tw.service.sys.impl;

import org.springframework.stereotype.Service;

import com.tw.dao.base.BaseDAOSupport;
import com.tw.entity.sys.Permission;
import com.tw.service.sys.PermissionService;
@Service("permissionService")
public class PermissionServiceImpl extends BaseDAOSupport<Permission> implements PermissionService{

}
