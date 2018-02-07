package com.free.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author      ludm  
 * @date        2017年12月28日下午3:12:39 
 * @description 基础的Controller
 */
public class BaseController {
	/**
	 * 日志对象,子类直接调用记录日志信息
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    protected HttpServletRequest request;	//使用注入不会有线程安全问题
	
	@Autowired
	protected HttpServletResponse response;
	
	
}
