package com.free.service;

import java.util.List;
import java.util.Map;

import com.free.common.BaseService;
import com.free.model.User;

public interface IUserService extends BaseService<User, Integer>{
	List<Map<String, Object>> getUserInfo();
}
