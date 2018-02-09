package com.free.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 
 * @author      ludm  
 * @date        2018年2月8日下午4:21:28 
 * @description 用户和密码（包含验证码）令牌类
 */
public class UserPswToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	private String captcha;
	
	public UserPswToken() {
		super();
	}

	public UserPswToken(String username, String password, String captcha) {
		super(username, password);
		this.captcha = captcha;
	}
	
	public UserPswToken(String username, char[] password,
			boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
}