<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- ${pageContext.request.contextPath}:绝对路径 -->
<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resource/favicon.png" media="screen"/>
<!--[if lt IE 9]>
<script type="text/javascript" src="js/html5.js"></script>
<script type="text/javascript" src="js/respond.min.js"></script>
<script type="text/javascript" src="js/PIE_IE678.js"></script>
<![endif]-->

<link href="${pageContext.request.contextPath}/resource/css/H-ui.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resource/css/H-ui.login.css" rel="stylesheet" type="text/css" />

<title>Free-后台管理系统</title>
</head>
<body>
<div class="header"></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form action="/login/auth" method="post">
      <div class="formRow user">
        <input name="loginname" type="text" placeholder="账户" class="input_text input-big">
      </div>
      <div class="formRow password">
        <input name="password" type="text" placeholder="密码" class="input_text input-big">
      </div>
      <div class="formRow yzm">
        <input class="input_text input-big" name="captcha" type="text" placeholder="验证码" onBlur="if(this.value==''){this.value='验证码:'}" onClick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;">
        <img src="/genCaptcha" id="captcha"> <a href="javascript:;" onclick="change()">看不清，换一张</a> 
      </div>
      <div class="formRow">
        <label for="online">
          <input type="checkbox" name="online" id="online" value="">使我保持登录状态</label>
      </div>
      <div class="formRow">
        <input type="submit" class="btn radius btn-success btn-big" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
        <input type="reset" class="btn radius btn-default btn-big" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
      </div>
    </form>
  </div>
</div>
<script type="text/javascript">
	function change(){
		document.getElementById("captcha").src = "/genCaptcha?t="+Math.random();
	}
</script>
</body>
</html>






