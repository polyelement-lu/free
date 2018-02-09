package com.free.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.free.common.Constants;
import com.free.model.Permission;
import com.free.model.Role;
import com.free.model.User;
import com.free.service.IUserService;
/**
 * 
 * @author      ludm  
 * @date        2018年2月7日下午5:31:44 
 * @description 自定义Shiro验证用户登录的类 
 */
public class ShiroDbRealm extends AuthorizingRealm {
	
	@Autowired
	private IUserService userService;

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
		// 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
		String loginName = (String)super.getAvailablePrincipal(principals);
		User userByLogin = userService.getUserByLoginName(loginName);
		if(userByLogin == null){
			throw new AuthenticationException("msg:用户不存在。");
		}
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		List<Role> roleByUserId = userService.getRoleByUserId(userByLogin.getId());
		List<Permission> permissionByUserId = userService.getPermissionByUserId(userByLogin.getId());
		
		if(roleByUserId != null && roleByUserId.size() > 0){
			for (Role role : roleByUserId) {
				if(role.getRoleCode() != null){
					simpleAuthorInfo.addRole(role.getRoleCode());
				}
			}
		}
		
		if(permissionByUserId != null && permissionByUserId.size() > 0){
			for (Permission permission : permissionByUserId) {
				if(permission.getCode() != null){
					simpleAuthorInfo.addStringPermission(permission.getCode());
				}
			}
		}
		return simpleAuthorInfo;	
	}

	
	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
		// 获取基于用户名和密码的令牌
		// 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		UserPswToken token = (UserPswToken)authcToken;
		Session session = getSession();
		String code = (String)session.getAttribute(Constants.VALIDATE_CODE);
		if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(code)){
			throw new AuthenticationException("msg:验证码错误, 请重试.");
		}
		
		User user = userService.getUserByLoginName(token.getUsername());
		if(user != null){
			if(user.getIslock() !=null && user.getIslock() == 1){
				throw new AuthenticationException("msg:该已帐号禁止登录。");
			}
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), this.getName());
			// 将用户放到session里面
			this.setSession("currentUser", user.getLoginName());
			return authcInfo;
		}
		return null;
	}
	
	/**
	 * 保存登录名
	 */
	private void setSession(Object key, Object value){
		Session session = getSession();
		System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
		if(null != session){
			session.setAttribute(key, value);
		}
	}
	
	private Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
		}catch (InvalidSessionException e){
			e.printStackTrace();
		}
		return null;
	}

}
