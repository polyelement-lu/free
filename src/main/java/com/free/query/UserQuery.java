package com.free.query;

import com.free.common.BaseQuery;
import com.free.model.User;

public class UserQuery extends BaseQuery<User>{
	
	private static final long serialVersionUID = -8843638950334254481L;
	private String name;
	private String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
