<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>旧生书城-商品详情</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.12.4.min.js"></script>
</head>
<script type="text/javascript">
	$(function(){
		$("#addnum").click(function(){
			var num=parseInt($("#num").val());
			num++;
			$("#num").val(num);
			var price=${book.bprice};
			var totalprice=(num*parseFloat(price,2)).toFixed(2);
			$("#bprice").html(totalprice);
		})
		
		$("#reducenum").click(function(){
			var num=parseInt($("#num").val());
			if(num==0)num=1;
			num--;
			$("#num").val(num);
			var price=${book.bprice};
			var totalprice=(num*parseFloat(price,2)).toFixed(2);
			$("#bprice").html(totalprice);
		})
	})
		
	function addCart(uid,bid){
		var bnum=$("#num").val();
		$.ajax({
			url:"<%=request.getContextPath()%>/addToCart.do",
			type:"POST",
			dataType:"json",
			data:"uid="+uid+"&bid="+bid+"&bnum="+bnum,
			success:function(a){
				alert(a.result);	
				$("#show_count").html(a.detailnum);
			}
		});
		
	}
	
	function buynow(){
		$("#buy").submit();
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
		<a href="<%=request.getContextPath()%>/toIndex.do" class="logo fl"><img src="<%=request.getContextPath()%>/images/logo.png" style="width: 160px; height: 53px;"></a>
		<div class="search_con fl">
			<input type="text" class="input_text fl" name="" placeholder="搜索商品">
			<input type="button" class="input_btn fr" name="" value="搜索">
		</div>
		<div class="guest_cart fr">
			<a href="<%=request.getContextPath()%>/toCart.do" class="cart_name fl">我的购物车</a>
			<div class="book_count fl" id="show_count">${sessionScope.common.detailnum}</div>
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
		<span>></span>
		<a href="<%=request.getContextPath()%>/findAllBook.do">${book.btype}</a>
		<span>></span>
		<a href="#">商品详情</a>
	</div>	
	<form action="<%=request.getContextPath()%>/buynow.do" method="post" id="buy">
		<div class="book_detail_con clearfix">
			<div class="book_detail_pic fl"><img src="<%=request.getContextPath()%>/${book.imgpath}"></div>
			<div class="book_detail_list fr">
				<input type="hidden" name="bid" value="${book.id}"/>
				<h3>${book.bname}</h3>
				<p>${book.bdesc}</p>
				<div class="prize_bar">
					<span class="show_pirze">¥<em>${book.bprice}</em></span>
					<span class="show_unit">单  位：册</span>
				</div>
				<div class="book_num clearfix">
					<div class="num_name fl">数 量：</div>
					<div class="num_add fl">
						<input name="bnum" id="num" type="text" class="num_show fl" value="1">
						<a id="addnum" href="javascript:;" class="add fr">+</a>
						<a id="reducenum" href="javascript:;" class="minus fr">-</a>	
					</div> 
				</div>
				<div class="total">总价：<em id="bprice">${book.bprice}</em></div>
				<div class="operate_btn">
					<a href="javascript:;" class="buy_btn" onclick="buynow()">立即购买</a>
					<a href="javascript:;" class="add_cart" id="add_cart" onclick="addCart(${sessionScope.user.id},${book.id})">加入购物车</a>				
				</div>
			</div>
		</div>
	</form>
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
			<ul class="detail_tab clearfix">
				<li class="active">商品介绍</li>
				<li>评论</li>
			</ul>

			<div class="tab_content">
				<dl>
					<dt>商品详情：</dt>
					<dd>${book.bdesc}</dd>
				</dl>
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
	<div class="add_jump"></div>

	<script type="text/javascript" src="js/jquery-1.12.2.js"></script>
	<script type="text/javascript">
	</script>
	
</body>
</html>