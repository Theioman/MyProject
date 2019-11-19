﻿<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>尚硅谷书城-登录</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
</head>
<%
Cookie[] cookies=request.getCookies();
String username=null;
String password=null;
if(cookies!=null)
{
	for(Cookie c:cookies)
	{
		if(c.getName().equals("username"))
		{
			username=c.getValue();//""
		}
		if(c.getName().equals("password"))
		{
			password=c.getValue();
		}
	}
}
if(username!=null&&password!=null&&!"".equals(username)&&!"".equals(password))
{
	response.sendRedirect(request.getContextPath()+"/login.do?username="+username+"&password="+password);
}
%>
<body>
	<div class="login_top clearfix">
		<a href="<%=request.getContextPath()%>/toIndex.do" class="login_logo"><img src="images/logo.png" style="width: 160px; height: 53px;"></a>	
	</div>

	<div class="login_form_bg">
		<div class="login_form_wrap clearfix">
			<div class="login_banner fl"></div>
			<div class="slogan fl">学计算机 · 删库跑路</div>
			<div class="login_form fr">
				<div class="login_title clearfix">
					<h1>用户登录</h1>
					<a href="${pageContext.request.contextPath}/toRegister.do">立即注册</a>
				</div>
				<div class="form_input">
					<form action="${pageContext.request.contextPath}/login.do" method="post">
						<input type="text" name="username" class="name_input" placeholder="请输入用户名">
						<div class="user_error">输入错误</div>
						<input type="password" name="password" class="pass_input" placeholder="请输入密码">
						<div class="pwd_error">输入错误</div>
						<div class="more_input clearfix">
							<input type="checkbox" name="autoLogin">
							<label>自动登陆</label>
							<a href="#">忘记密码</a>
						</div>
						<input type="submit" name="" value="登录" class="input_submit">
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="footer no-mp">
		<div class="foot_link">
			<a href="#">关于我们</a>
			<span>|</span>
			<a href="#">联系我们</a>
			<span>|</span>
			<a href="#">招聘人才</a>
			<span>|</span>
			<a href="#">友情链接</a>		
		</div>
		<p>CopyRight © 2016 北京旧生信息技术有限公司 All Rights Reserved</p>
		<p>电话：010-****888    京ICP备*******8号</p>
	</div>
	
</body>
</html>