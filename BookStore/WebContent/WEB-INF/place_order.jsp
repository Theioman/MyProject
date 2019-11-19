<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>旧生书城-提交订单</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">
	function findCondition(){
		var condition=$("#condition").val();
		location.href='<%=request.getContextPath()%>/pageRequest.do?condition='+condition;
	}</script>
</head>
<body>
	<div class="header_con">
		<div class="header">
			<div class="welcome fl">欢迎来到旧生书城!</div>
			<div class="fr">
				<div class="login_info fl">
					欢迎您：<em>${sessionScope.user.username}</em>
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
		<div class="sub_page_name fl">|&nbsp;&nbsp;&nbsp;&nbsp;提交订单</div>
		<div class="search_con fr">
			<input type="text" class="input_text fl" name="" id="condition" placeholder="搜索商品">
			<input type="button" class="input_btn fr" name="" value="搜索" onclick="findCondition()">		</div>		
	</div>
	
	<h3 class="common_title">确认收货地址</h3>

	<div class="common_list_con clearfix">
		<dl>
			<dt>寄送到：</dt>
			<dd><input type="radio" name="" checked="">${info.content}（${sessionScope.user.username} 收） ${info.phone}</dd>
		</dl>
		<a href="<%=request.getContextPath()%>/queryUsersSite.do" class="edit_site">编辑收货地址</a>

	</div>
	
	<h3 class="common_title">支付方式</h3>	
	<div class="common_list_con clearfix">
		<div class="pay_style_con clearfix">
			<input type="radio" name="pay_style" checked>
			<label class="cash">货到付款</label>
			<input type="radio" name="pay_style">
			<label class="weixin">微信支付</label>
			<input type="radio" name="pay_style">
			<label class="zhifubao"></label>
			<input type="radio" name="pay_style">
			<label class="bank">银行卡支付</label>
		</div>
	</div>

	<h3 class="common_title">商品列表</h3>
	<form action="<%=request.getContextPath()%>/addOrder.do?totalprice=${pn.totalprice+10}&uid=${sessionScope.user.id}" method="post" id="form1">
		<div class="common_list_con clearfix">
			<ul class="book_list_th clearfix">
				<li class="col01">商品名称</li>
				<li class="col02">商品单位</li>
				<li class="col03">商品价格</li>
				<li class="col04">数量</li>
				<li class="col05">小计</li>		
			</ul>
			<c:forEach items="${Commons}" var="ca" varStatus="index">
				<ul class="book_list_td clearfix">
					<input type="hidden" name="bid" value="${ca.bid}"/>
					<li class="col01">${index.index+1}</li>			
					<li class="col02"><img src="${ca.imgpath}" /></li>
					<li class="col03">${ca.bname}</li>
					<li class="col04">册</li>
					<li class="col05">${ca.bprice}元</li>
					<input type="hidden" name="bnum" value="${ca.bnum}"/>
					<li class="col06">${ca.bnum}</li>
					<li class="col07"><fmt:formatNumber type="number" value="${ca.bprice*ca.bnum}" maxFractionDigits="2"></fmt:formatNumber>元</li>	
				</ul>
			</c:forEach>
		</div>
	
		<h3 class="common_title">总金额结算</h3>
	
		<div class="common_list_con clearfix">
			<div class="settle_con">
				<div class="total_book_count">共<em>${pn.detailnum}</em>件商品，总金额<b>${pn.totalprice}元</b></div>
				<div class="transit">运费：<b>10元</b></div>
				<div class="total_pay">实付款：<b id="sbprice">${pn.totalprice+10}元</b></div>
			</div>
		</div>
	
		<input type="hidden" name="uid" value="${sessionScope.user.id}"/>
		<div class="order_submit clearfix">
			<a href="#" onclick="formsubmit()">提交订单</a>
		</div>	
	</form>
	<script type="text/javascript">
		function formsubmit(){
			$("#form1").submit();
		}
	</script>
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
		<p>CopyRight © 2016 北京旧生信息技术有限公司 All Rights Reserved</p>
		<p>电话：010-****888    京ICP备*******8号</p>
	</div>

	<div class="popup_con">
		<div class="popup">
			<p>订单提交成功！</p>
		</div>
		
		<div class="mask"></div>
	</div>
	<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">
		$('#order_btn').click(function() {
			$('.popup_con').fadeIn('fast', function() {
				setTimeout(function(){
					$('.popup_con').fadeOut('fast',function(){
						window.location.href = '<%=request.getContextPath()%>/toIndex.do';
					});	
				},3000)
			});
		});
	</script>
</body>
</html>