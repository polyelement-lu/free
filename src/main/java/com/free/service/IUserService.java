package com.free.service;

import java.util.List;

import com.free.common.BaseService;
import com.free.model.Permission;
import com.free.model.Role;
import com.free.model.User;
/**
 * 
 * @author      ludm  
 * @date        2018年2月8日下午2:04:23 
 * @description 用户信息service
 */
public interface IUserService extends BaseService<User, Integer>{
	
	/**
	 * 根据登录账号获取用户信息
	 * @param loginName
	 * @return
	 */
	User getUserByLoginName(String loginName);
	
	/**
	 * 用户登录
	 * @param loginName
	 * @param psw
	 * @return
	 */
	User doUserLogin(String loginName,String psw);
	
	/**
	 * 根据用户ID获取该用户角色信息
	 * @param userId
	 * @return
	 */
	List<Role> getRoleByUserId(Integer userId);
	
	/**
	 * 根据用户ID获取该用户权限信息
	 * @param userId
	 * @return
	 */
	List<Permission> getPermissionByUserId(Integer userId);
}
