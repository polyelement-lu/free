<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/ace/css/bootstrap.min.css" /> --%>
<link href="${pageContext.request.contextPath}/resource/layui/css/layui.css" rel="stylesheet" type="text/css" />
<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resource/favicon.png" media="screen"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/ace/font-awesome/4.5.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/main.css" />

<title>Free-后台管理系统</title>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">layui 后台布局</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">商品管理</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
		  <!-- 默认分割线 -->
		  <hr>
          <dd><a href="">测试</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
        超级管理员
        </a>
        <dl class="layui-nav-child">
          <dd><a href=""><i class="ace-icon fa fa-user"></i>基本资料</a></dd>
          <dd><a href=""><i class="ace-icon fa fa-cog"></i>安全设置</a></dd>
          <hr>
          <dd><a href=""><i class="ace-icon fa fa-power-off" ></i>注销</a></dd>
        </dl>
      </li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;"><i class="menu-icon fa fa-tachometer"></i>所有商品</a>
          <dl class="layui-nav-child">
            <dd class="layui-nav-item layui-nav-itemed">
            	<a href="javascript:;">列表一</a>
            	<dl class="layui-nav-child">
	            	<dd>
	            		<a class="" href="javascript:;">子列表一</a>
	            	</dd>
            	</dl>
            </dd>
            <dd><a href="javascript:;">列表二</a></dd>
            <dd><a href="javascript:;">列表三</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;"><i class="menu-icon fa fa-desktop"></i>解决方案</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">列表一</a></dd>
            <dd><a href="javascript:;">列表二</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="javascript:;"><i class="menu-icon fa fa-list"></i>云市场</a></li>
        <li class="layui-nav-item"><a href="javascript:;"><i class="menu-icon fa fa-list-alt"></i>发布商品</a></li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
    	内容主体区域
    	<br><br>
      <blockquote class="layui-elem-quote">
        layui 之所以赢得如此多人的青睐，更多是在于它“前后台系统通吃”的能力。既可编织出绚丽的前台页面，又可满足繁杂的后台功能需求。
        <br>layui 后台布局， 致力于让每一位开发者都能轻松搭建自己的后台模板。
      </blockquote>
      
      <a href="/doc/element/layout.html#admin" target="_blank" class="layui-btn layui-btn-big">获取该布局代码</a>
      
      <br><br><br><br>
      
       
      
      
      下面是充数内容，为的是出现滚动条<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>
    </div>
  </div>
  
  <div class="layui-footer" style="text-align: center;">
    <!-- 底部固定区域 -->
    © 2017 - 玩的就是心跳
  </div>
</div>
<script src="${pageContext.request.contextPath}/resource/layui/layui.js""></script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});
</script>
</body>
</html>