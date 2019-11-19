<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE >
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>书城-商品列表</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.4.min.js"></script>

	<script type="text/javascript">
	$(function(){
		var pagenum=${pagenum};
		$("#page").children('a').eq(pagenum).addClass("active");
	});
		function findCondition(){
			var condition=$("#condition").val();
			location.href='<%=request.getContextPath()%>/pageRequest.do?condition='+condition;
		};
	</script>
</head>
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
		<div class="search_con fl">
			<input type="text" class="input_text fl" id="condition" placeholder="搜索商品">
			<input type="button" class="input_btn fr" name="" value="搜索" onclick="findCondition()"/>
		</div>
		<div class="guest_cart fr">
			<a href="<%=request.getContextPath()%>/toCart.do" class="cart_name fl">我的购物车</a>
			<div id="show_count" class="book_count fl">${sessionScope.common.detailnum}</div>
		</div>
	</div>

	<div class="navbar_con">
		<div class="navbar clearfix">
			<div class="subnav_con fl">
				<h1>全部商品分类</h1>	
				<span></span>			
				<ul class="subnav">
					<li><a href="<%=request.getContextPath()%>/findBookByType.do?btype=Python" class="python">Python</a></li>
					<li><a href="<%=request.getContextPath()%>/findBookByType.do?btype=Javascript" class="javascript">Javascript</a></li>
					<li><a href="<%=request.getContextPath()%>/findBookByType.do?btype=数据结构与算法" class="algorithms">数据结构与算法</a></li>
					<li><a href="<%=request.getContextPath()%>/findBookByType.do?btype=机器学习" class="machinelearning">机器学习</a></li>
					<li><a href="<%=request.getContextPath()%>/findBookByType.do?btype=操作系统" class="operatingsystem">操作系统</a></li>
					<li><a href="<%=request.getContextPath()%>/findBookByType.do?btype=数据库" class="database">数据库</a></li>
				</ul>
			</div>
			<ul class="navlist fl">
				<li><a href="<%=request.getContextPath()%>/toIndex.do">首页</a></li>
				<li class="interval">|</li>
				<li><a href="">移动端书城</a></li>
				<li class="interval">|</li>
				<li><a href="">秒杀</a></li>
			</ul>
		</div>
	</div>

	<div class="breadcrumb">
		<a href="<%=request.getContextPath()%>/findAllBook.do">全部分类</a>
	</div>

	<div class="main_wrap clearfix">
		<div class="l_wrap fl clearfix">
			<div class="new_book">
				<h3>新品推荐</h3>
				<ul>
					<li>
						<a href="#"><img src="images/book/book001.jpg"></a>
						<h4><a href="#">Python核心编程</a></h4>
						<div class="prize">￥3.90</div>
					</li>
					<li>
						<a href="#"><img src="images/book/book002.jpg"></a>
						<h4><a href="#">Python学习手册</a></h4>
						<div class="prize">￥16.80</div>
					</li>
				</ul>
			</div>
		</div>

		<div class="r_wrap fr clearfix">
			<div class="sort_bar">
				<a style="cursor:pointer" class="active">默认</a>
				<a style="cursor:pointer" >价格</a>
				<a style="cursor:pointer" >人气</a>
			</div>

			<ul id="booklist" class="book_type_list clearfix">
				<c:forEach items="${books}" var="b">
					<li>
						<a href="<%=request.getContextPath()%>/bookDetail.do?id=${b.id}"><img src="${b.imgpath}"></a>
						<h4><a href="detail.html">${b.bname}</a></h4>
						<div class="operate">
							<span class="prize">￥${b.bprice}</span>
							<span class="unit">/册</span>
							<a style="cursor:pointer" class="add_book" title="加入购物车" onclick="toAddCart(${sessionScope.user==null?-1:sessionScope.user.id},${b.id})"></a>
						</div>
					</li>	
				</c:forEach>
			</ul>

			<div class="pagenation" id="page">
				<a href="<%=request.getContextPath()%>/pageRequest.do?pagenum=${pagenum-1}&condition=${condition}&btype=${btype}">&lt上一页</a>
				<%
					Object pagecount=request.getAttribute("page");
					Integer count=0;
					if(pagecount!=null){
						count=(Integer)pagecount;
					}
					for(int i=1;i<=count;i++){
				%>
				<a href="<%=request.getContextPath()%>/pageRequest.do?pagenum=<%=i%>&condition=${condition}&btype=${btype}"><%=i%></a>
				<%} %>
				<a href="<%=request.getContextPath()%>/pageRequest.do?pagenum=${pagenum+1}&condition=${condition}&btype=${btype}">下一页></a>
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
		<p>CopyRight © 2016 北京旧生信息技术有限公司 All Rights Reserved</p>
		<p>电话：010-****888    京ICP备*******8号</p>
	</div>
	
</body>
	<script type="text/javascript">
	function toAddCart(uid,bid){
		$.ajax({
			url:"<%=request.getContextPath()%>/addToCart.do",
			type:"POST",
			dataType:"json",
			data:"uid="+uid+"&bid="+bid+"&bnum=1",
			success:function(a){
				alert(a.result);
				$("#show_count").html(a.detailnum);
			}
		});
	}
	</script>
</html>