<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/head.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.js"></script>
</head>
<script language="javascript">
	function UserLogin(){
	
	
	//window.navigate("Admin/Index.html");不符合mvc三层架构，所有请求都必须先提交给servlet
	//登录请求：表单请求UserServlet
	document.getElementById("form1").submit();
	
	}
</script>
<body>
	<div class="dvhead">
		<div class="dvlogo">
			<a href="index.jsp">你问我答</a>
		</div>
		<div class="dvsearch" style="color:white">10秒钟注册账号，找到你的同学</div>
	</div>
	<section class="sec">
		<form action="<%=request.getContextPath() %>/UserServlet?operator=login"
			method="post" id="form1">
			<div class="register-box">
				<label for="username" class="username_label"> 用 户 名
				 <input maxlength="20" name="username" type="text" placeholder="您的用户名和登录名" style="border:none;height: 25px" />
				</label>
				<div class="tips"></div>
			</div>
			<div class="register-box">
				<label for="username" class="other_label"> 密&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;码&nbsp;
				 <input maxlength="20" type="password" name="password" placeholder="建议至少使用两种字符组合" style="border:none;height: 25px"/>
				</label>
				<div class="tips"><s:property value="error"/> </div>
			</div>
			<div class="register-box">
				<label for="username" class="username_label"> 验 证 码
				 <input maxlength="20" name="code" type="text" placeholder="请输入验证码" style="border:none;height: 25px" />
				 <img src="ValidateCodeServlet" alt="" height="20" onclick="shuaxin_code()" id="validateCode" onclick="shuaxin_code()"/>
				</label>
				<div class="tips"></div>
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
				<input type="checkbox" id="xieyi" name="readAgree"  onclick="if (this.checked) {enable()} else {disable()}"/> 阅读并同意 <a
					href="javascript:void(0)">《你问我答用户注册协议》</a> <a href="register.jsp">没有账号,立即注册</a>
				<div class="tips"></div>
			</div>
			<div class="submit_btn">
				<button type="button" id="accept" onclick="UserLogin()"  disabled="true" style="background: #e5e5e5">立 即 登录</button><%=request.getAttribute("mess") %>
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
			<script>
			/* $("#xieyi").change(function(){
		        if($("input[type='checkbox']").is(':checked')==true){
		            $("#submit_btn").attr("disabled",false);
		            $("#submit_btn").css("background","#c94d31");
		            $("#submit_btn").checked=false;
		        }else{
		            $("#submit_btn").attr("disabled",true);
		            $("#submit_btn").css("background","#e5e5e5");
		            $("#submit_btn").checked=true;
		        }
		    });  */
		    /* function disable()
		      {
		      document.getElementById("submit_btn").disabled=true
		      }
		    function enable()
		      {
		      document.getElementById("submit_btn").disabled=false
		      } */

		     </script>
		</form>
	</section>
	<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
</body>