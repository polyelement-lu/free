<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/login/auth" method="post">
		<div>
			<label>用户名</label><input type="text" name="loginname" />
		</div>
		<div>
			<label>密 &nbsp;&nbsp;码</label><input type="text" name="password" />
		</div>
		<div>
			<label>验证码</label><input type="text" name="captcha" /><img
				src="/genCaptcha" />
		</div>
		<div>
			<input type="submit" value="登录" />
		</div>
	</form>
</body>
</html>