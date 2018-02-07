package com.free.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.free.common.BaseServiceImpl;
import com.free.mapper.UserMapper;
import com.free.model.User;
import com.free.service.IUserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements IUserService{
	@Autowired
	private UserMapper userMapper;
	@Override
	public List<Map<String, Object>> getUserInfo() {
		// TODO Auto-generated method stub
		return userMapper.getUserInfo();
	}

}
