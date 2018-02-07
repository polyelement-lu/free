package com.free.mapper;

import java.util.List;
import java.util.Map;

import com.free.common.dao.mapper.BaseMapper;
import com.free.model.User;

public interface UserMapper extends BaseMapper<User>{
	List<Map<String, Object>> getUserInfo();
}
