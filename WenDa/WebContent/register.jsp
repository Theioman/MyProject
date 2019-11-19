<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>注册信息</title>
	<link rel="stylesheet" type="<%=request.getContextPath() %>/text/css" href="css/login.css" />
		 <link rel="stylesheet" href="<%=request.getContextPath() %>/css/head.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
	<script>
//写js校验注册表单
$().ready(function()//window.onload()
{
	//给表单注册校验事件
	$("#registerForm").validate({
		
		rules:{
			username:"required",
			password:"required",
			password_confirm:{
				required:true,
				equalTo:"#password",
			},
		},messages:{
			username:"请输入您的名字",
			password:"请输入密码",
			password_confirm:{
					required:"请输入确认密码",	
					equalTo:"两次密码输入不一致"
			},
		}
	});
});
</script>
</head>
<script language="javascript">
	function UserRegister(){
	
	
	//window.navigate("Admin/Index.html");不符合mvc三层架构，所有请求都必须先提交给servlet
	//登录请求：表单请求UserServlet
	document.getElementById("registerForm").submit();
	
	}
</script>
<body>
<div class="dvhead">
	<div class="dvlogo"><a href="<%=request.getContextPath() %>/index.jsp">你问我答</a></div>
	<div class="dvsearch">10秒钟注册账号，找到你的同学</div>
	<div class="dvreg">
		已有账号，立即&nbsp;<a href="<%=request.getContextPath() %>/login.jsp">登录</a>
	</div>
</div>
		<section class="sec">
            <form action="UserServlet?operator=register" method="post" id="registerForm">
			<div class="register-box">
				<label for="username" class="username_label">
					用 户 名
					<input maxlength="20"  type="text" name="username" id="username"
						placeholder="您的用户名和登录名" />
				</label>
				<div class="tips">

				</div>
			</div>
			<div class="register-box">
				<label for="username" class="other_label">
					设 置 密 码
					<input maxlength="20" type="password" name ="password" id="password"
						placeholder="建议至少使用两种字符组合" />
				</label>
				<div class="tips">

				</div>
			</div>
			<div class="register-box">
				<label for="username" class="other_label">
					确 认 密 码
					<input maxlength="20" type="password" name ="password_com" placeholder="请再次输入密码" />
				</label>
				<div class="tips">

				</div>
			</div>
			
			<div class="register-box">
				<label for="username" class="username_label">
					邮箱
					<input maxlength="20" type="text" name="email" id="email"
						placeholder="您的邮箱" />
				</label>
				<div class="tips">
				</div>
			</div>
			<div class="register-box">
				<label for="username" class="username_label">
					手机号
					<input maxlength="20"  type="text" name="phone" id="phone"
						placeholder="您的手机号" />
				</label>
				<div class="tips">
				</div>
			</div>
	
			<div class="register-box">
				<label for="username" class="other_label">
					验 证 码
					<input maxlength="20" type="text" placeholder="请输入验证码" name="code"/>
					<img src="ValidateCodeServlet" alt="" height="20" onclick="shuaxin_code()" id="validateCode" onclick="shuaxin_code()"/>
				</label>
				
			</div>
			 <script>
		      function shuaxin_code()
		      {
		    	  //alert();
		    	  var date=new Date();
		    	  
		    	  document.getElementById("validateCode").src="ValidateCodeServlet?date="+date;
		      }
		     </script>
			<div class="arguement">
				<input type="checkbox" id="xieyi" name="readAgree"  onclick="if (this.checked) {enable()} else {disable()}"/>
				阅读并同意
				<a href="javascript:void(0)">《错题用户注册协议》</a>
				<a href="login.html">已有账号,立即登录</a>
				<div class="tips">
				</div>
			</div>
			<div class="submit_btn">
				<button type="button" id="accept" onclick="UserRegister()"  disabled="true" style="background: #e5e5e5">立 即 注册</button><%=request.getAttribute("mess") %>
			</div>
			<script type="text/javascript">
				function disable()
				  {
				  document.getElementById("accept").disabled=true;
				  document.getElementById("accept").style.background='#e5e5e5';
				  }
				function enable()
				  {
				  document.getElementById("accept").disabled=false;
				  document.getElementById("accept").style.background='blue';
				  }
			</script>
		</form>
		</section>
		<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
