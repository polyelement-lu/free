package com.free.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.free.common.BaseServiceImpl;
import com.free.mapper.UserMapper;
import com.free.model.Permission;
import com.free.model.Role;
import com.free.model.User;
import com.free.service.IUserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements IUserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserByLoginName(String loginName) {
		return userMapper.getUserByLoginName(loginName);
	}

	@Override
	public User doUserLogin(String loginName, String psw) {
		return userMapper.doUserLogin(loginName, psw);
	}

	@Override
	public List<Role> getRoleByUserId(Integer userId) {
		return userMapper.getRoleByUserId(userId);
	}

	@Override
	public List<Permission> getPermissionByUserId(Integer userId) {
		return userMapper.getPermissionByUserId(userId);
	}

}
