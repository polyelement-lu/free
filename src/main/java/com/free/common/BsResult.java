package com.free.common;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author      ludm  
 * @date        2018年2月9日下午4:53:18 
 * @description bootstrap的分页返回结果
 * @param <T>
 */
public class BsResult<T> extends Result<T> {
	private static final long serialVersionUID = -4967544606703872797L;
	
	public BsResult(int code) {
		this.code = code;
	}
	
	public BsResult(int code, String msg) {
		this(code);
		this.msg = msg;
	}
	
	public BsResult(int code, String msg,T data) {
		this(code,msg);
		this.data = data;
	}
	
	public BsResult(int code, String msg,T data,Long total) {
		this(code,msg,data);
		this.total = total;
	}
	
	@Override
	@JSONField(name="rows")
	public T getData() {
		// TODO Auto-generated method stub
		return super.getData();
	}

}
