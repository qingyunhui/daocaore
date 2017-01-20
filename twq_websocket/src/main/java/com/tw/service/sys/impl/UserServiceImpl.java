package com.tw.service.sys.impl;

import org.springframework.stereotype.Service;

import com.tw.dao.base.BaseDAOSupport;
import com.tw.entity.sys.User;
import com.tw.service.sys.UserService;

@Service("userService")
public class UserServiceImpl extends BaseDAOSupport<User> implements UserService{

}
