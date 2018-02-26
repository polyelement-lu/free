package com.free.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.free.common.BaseController;
import com.free.common.Constants;
import com.free.common.Result;
import com.free.common.dao.util.ResultUtil;
import com.free.service.impl.UserServiceImpl;
import com.free.shiro.UserPswToken;
import com.free.utils.VerifyCodeUtil;

import net.sf.ehcache.transaction.xa.processor.XARequest.RequestType;
/**
 * 
 * @author      ludm  
 * @date        2018年2月9日下午3:35:15 
 * @description 用户登录
 */
@Controller
public class LoginController extends BaseController{
	@Autowired
	private UserServiceImpl userService;

	@RequestMapping("login")
	public String login(HttpServletRequest request){
		return "login";
	}
	
	@RequestMapping(value="login/auth",method=RequestMethod.POST)
	@ResponseBody
	public Result<?> doLogin(HttpServletRequest request){
		String username = request.getParameter("loginname");
		String pwd = request.getParameter("password");
		String captcha = request.getParameter("captcha");
		if(StringUtils.isBlank(username) || StringUtils.isBlank(pwd)){
			return ResultUtil.doSuccess("请输入【账户】或【密码】！");
		}

		UserPswToken token = new UserPswToken(username, pwd, captcha); 
		Subject currentUser = SecurityUtils.getSubject(); 
		try {
			currentUser.login(token);
		} catch (IncorrectCredentialsException e) {
			return ResultUtil.doSuccess("【用户】或【密码】错误，请重试！");
		} catch (AuthenticationException e) {
			return ResultUtil.doSuccess(e.getMessage());
		} 
		return ResultUtil.response(201, "/admin/index");
	}
	
	@RequestMapping("admin/index")
	public String index(HttpServletRequest request){
		return "main";
	}
	@ResponseBody
	@RequestMapping("admin/channel")
	public String channel(HttpServletRequest request){
		return "wellcome channel";
	}
	@ResponseBody
	@RequestMapping("admin/content")
	public String content(HttpServletRequest request){
		return "wellcome content";
	}
	@ResponseBody
	@RequestMapping("admin/sys")
	public String sys(HttpServletRequest request){
		return "wellcome sys";
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		SecurityUtils.getSubject().logout();  
		return "redirect:/login";
	}
	
	/** 
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中) 
     */  
    @RequestMapping("/genCaptcha")  
    public void genCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {  
        //设置页面不缓存  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);  
        //将验证码放到HttpSession里面  
        request.getSession().setAttribute(Constants.VALIDATE_CODE, verifyCode);  
        //设置输出的内容的类型为JPEG图像  
        response.setContentType("image/jpeg");  
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 5, true, Color.WHITE, null, null);  
        //写给浏览器  
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());  
    } 
}
