package com.free.common;

import java.io.Serializable;

/**
 * 
 * @author ludm
 * @date 2018年2月9日下午4:44:51
 * @description 通用的返回结果
 * @param <T>
 */
public class Result<T> implements Serializable {
	private static final long serialVersionUID = 786076110904761931L;

	public static final int OK = 200; // 默认响应成功码200
	public static final int INTERNAL_SERVER_ERROR = 500; // 默认响应失败码500
	public static final int NO_CONTENT = 204; // 未找到资源204
	public static final int EXPECTATION_FAILED = 417; // 参数异常请求失败码417
	public static final int UNAUTHORIZED = 401; // 未认证权限401
	public static final String ERROR_MSG = "抱歉,服务器异常,请联系管理员!"; // 默认错误提示消息
	
	// 定义带业务属性的状态码
	protected int code; // 服务器响应码
	protected String msg; // 响应消息
	protected Long total; // 请求数据的总记录数--包装类 --fastjson解析的时候null值不会解析
	protected T data; // 数据对象

	public Result() {
	}

	public Result(int code) {
		this.code = code;
	}

	public Result(int code, String msg) {
		this(code);
		this.msg = msg;
	}

	public Result(int code, String msg, T data) {
		this(code, msg);
		this.data = data;
	}

	public Result(int code, String msg, T data, Long total) {
		this(code, msg, data);
		this.total = total;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
