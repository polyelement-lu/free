package com.free.common.dao.util;

import com.free.common.BsResult;
import com.free.common.Result;

/**
 * 
 * @author ludm
 * @date 2018年2月9日下午4:54:36
 * @description bootstrap String 结果返回工具
 */
public class BsResultUtil {

	public static <T> BsResult<T> doSuccess() {
		return doSuccess(null, null, null);
	}

	public static <T> BsResult<T> doSuccess(String msg) {
		return doSuccess(msg, null, null);
	}

	public static <T> BsResult<T> doSuccess(T data) {
		return doSuccess(null, data, null);
	}

	public static <T> BsResult<T> doSuccess(String msg, T data) {
		return doSuccess(msg, data, null);
	}

	public static <T> BsResult<T> doSuccess(T data, Long total) {
		return doSuccess(null, data, total);
	}

	public static <T> BsResult<T> doSuccess(String msg, T data, Long total) {
		return new BsResult<>(Result.OK, msg, data, total);
	}

	// 服务器异常
	public static <T> BsResult<T> doFailure() {
		return doFailure(null);
	}

	public static <T> BsResult<T> doFailure(String msg) {
		return new BsResult<>(Result.INTERNAL_SERVER_ERROR, msg);
	}

	// 未找到资源
	public static <T> BsResult<T> notFound() {
		return notFound(null);
	}

	public static <T> BsResult<T> notFound(String msg) {
		return new BsResult<>(Result.NO_CONTENT, msg);
	}

	// 请求参数不正确的异常
	public static <T> BsResult<T> requestException(int resultCode, String msg) {
		return new BsResult<>(resultCode, msg);
	}

	public static <T> BsResult<T> requestException(String msg) {
		return requestException(Result.EXPECTATION_FAILED, msg);
	}

	public static <T> BsResult<T> requestException() {
		return requestException(null);
	}

	// 自定义响应 Status
	public static <T> BsResult<T> response(int resultCode) {
		return response(resultCode, null);
	}

	public static <T> BsResult<T> response(int resultCode, String msg) {
		return response(resultCode, msg, null);
	}

	public static <T> BsResult<T> response(int resultCode, String msg, T data) {
		return new BsResult<>(resultCode, msg, data);
	}

}
