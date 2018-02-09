package com.free.common.dao.util;

import com.free.common.Result;

/**
 * 
 * @author      ludm  
 * @date        2018年2月9日下午4:46:02 
 * @description 返回结果的处理工具类
 */
public class ResultUtil {

	public static <T> Result<T> doSuccess() {
		return doSuccess(null, null, null);
	}

	public static <T> Result<T> doSuccess(String msg) {
		return doSuccess(msg, null, null);
	}

	public static <T> Result<T> doSuccess(T data) {
		return doSuccess(null, data, null);
	}

	public static <T> Result<T> doSuccess(String msg, T data) {
		return doSuccess(msg, data, null);
	}

	public static <T> Result<T> doSuccess(T data, Long total) {
		return doSuccess(null, data, total);
	}

	public static <T> Result<T> doSuccess(String msg, T data, Long total) {
		return new Result<>(Result.OK, msg, data, total);
	}

	// 服务器异常
	public static <T> Result<T> doFailure() {
		return doFailure(null);
	}

	public static <T> Result<T> doFailure(String msg) {
		return new Result<>(Result.INTERNAL_SERVER_ERROR, msg);
	}
	
	// 服务器异常
	public static <T> Result<T> doFailureMsg() {
		return doFailure(Result.ERROR_MSG);
	}


	// 未找到资源
	public static <T> Result<T> notFound() {
		return notFound(null);
	}
	
	public static <T> Result<T> notFound(String msg) {
		return new Result<>(Result.NO_CONTENT, msg);
	}
	
	// 请求参数不正确的异常
	public static <T> Result<T> requestException(int resultCode,String msg) {
		return new Result<>(resultCode, msg);
	}

	public static <T> Result<T> requestException(String msg) {
		return requestException(Result.EXPECTATION_FAILED, msg);
	}
	
	public static <T> Result<T> requestException() {
		return requestException(null);
	}

	
	// 自定义响应 Status
	public static <T> Result<T> response(int resultCode) {
		return response(resultCode, null);
	}

	public static <T> Result<T> response(int resultCode, String msg) {
		return response(resultCode, msg, null);
	}

	public static <T> Result<T> response(int resultCode, String msg, T data) {
		return new Result<>(resultCode, msg, data);
	}
}
