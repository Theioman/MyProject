<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>尚硅谷书城-用户中心</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.4.min.js"></script>
</head>
	<script type="text/javascript">
	function findCondition(){
		var condition=$("#condition").val();
		location.href='<%=request.getContextPath()%>/pageRequest.do?condition='+condition;
	}
	</script>
<body>
	<div class="header_con">
		<div class="header">
			<div class="welcome fl">欢迎来到旧生书城!</div>
			<div class="fr">
				<div class="login_info fl">
					欢迎您：<em>张 山</em>
				</div>
				<div class="login_btn fl">
			${sessionScope.user==null?"<a href='toLogin.do'>登录</a><span>|</span><a href='toRegister.do'>注册</a>":sessionScope.user.username}
				</div>
				<div class="user_link fl">
					<span>|</span>
					<a href="<%=request.getContextPath()%>/queryUsersInfo.do">用户中心</a>
					<span>|</span>
					<a href="<%=request.getContextPath()%>/toCart.do">我的购物车</a>
					<span>|</span>
					<a href="<%=request.getContextPath()%>/toOrder.do?uid=${sessionScope.user.id}">我的订单</a>
					<span>|</span>
					${sessionScope.user==null?"":"<a href='exit.do'>退出</a>"}
				</div>
			</div>
		</div>		
	</div>

	<div class="search_bar clearfix">
		<a href="<%=request.getContextPath()%>/toIndex.do" class="logo fl"><img src="images/logo.png" style="width: 160px; height: 53px;"></a>
		<div class="sub_page_name fl">|&nbsp;&nbsp;&nbsp;&nbsp;用户中心</div>
		<div class="search_con fr">
			<input type="text" class="input_text fl" id="condition" placeholder="搜索商品">
			<input type="button" class="input_btn fr" name="" value="搜索" onclick="findCondition()">
		</div>		
	</div>

	<div class="main_con clearfix">
		<div class="left_menu_con clearfix">
			<h3>用户中心</h3>
			<ul>
				<li><a href="<%=request.getContextPath()%>/queryUsersInfo.do">· 个人信息</a></li>
				<li><a href="<%=request.getContextPath()%>/toOrder.do?uid=${sessionScope.user.id}">· 全部订单</a></li>
				<li><a href="<%=request.getContextPath()%>/queryUsersSite.do" class="active">· 收货地址</a></li>
			</ul>
		</div>
		<div class="right_content clearfix">
				<h3 class="common_title2">收货地址</h3>
				<div class="site_con">
					<dl>
						<dt>当前地址：</dt>
						<dd>${usersSite.content}（${usersSite.addressee} 收）电话： ${usersSite.phone}</dd>
					</dl>					
				</div>
				<h3 class="common_title2">编辑地址</h3>
				<div class="site_con">
					<form action="<%=request.getContextPath()%>/${UsesSiteStatus}" method="post"  >
						<div class="form_group">
						
							<label>收件人：</label>
							<input type="text" name="addressee" value="${usersSite.addressee}">
						</div>
						<div class="form_group form_group2">
							<label>详细地址：</label>
							<textarea class="site_area" name="content" value="">${usersSite.content}</textarea>
						</div>
						<div class="form_group">
							<label>邮编：</label>
							<input type="text" name="mailCode" value="${usersSite.mailCode}">
						</div>
						<div class="form_group">
							<label>手机：</label>
							<input type="text" name="phone" value="${usersSite.phone}">
						</div>

						<input type="submit" name="" value="${bt_status}" class="info_submit" >
					</form>
				</div>
		</div>
	</div>



	<div class="footer">
		<div class="foot_link">
			<a href="#">关于我们</a>
			<span>|</span>
			<a href="#">联系我们</a>
			<span>|</span>
			<a href="#">招聘人才</a>
			<span>|</span>
			<a href="#">友情链接</a>		
		</div>
		<p>CopyRight © 2016 北京尚硅谷信息技术有限公司 All Rights Reserved</p>
		<p>电话：010-****888    京ICP备*******8号</p>
	</div>
	
</body>
</html>