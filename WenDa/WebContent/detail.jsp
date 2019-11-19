<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>问题详情</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="keywords" content="fly,layui,前端社区">
<meta name="description" content="">
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/layui/css/layui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/global.css">
<script src="<%=request.getContextPath()%>/res/layui/layui.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js"></script>

<style type="text/css" rel="stylesheet">
form {
	margin: 0;
}

.editor {
	margin-top: 5px;
	margin-bottom: 5px;
}
</style>
<script type="text/javascript">
	function delAnswer(id) {
		<%-- window.navigate("<%=request.getContextPath()%>/AnswerServlet?operator=allAnswer"); --%>
		if(confirm("确定删除该回答?")){
		location.href="<%=request.getContextPath()%>/AnswerServlet?operator=delAnswer&id="+ id +"&qid=${QuesId}";
		}
	}
	function delQuestion(){
		location.href="<%=request.getContextPath()%>/QuestionServlet?operator=delQuestion&qid=${QuesId}";
	}

	<%-- function dianzan(id){
		location.href="<%=request.getContextPath()%>/AnswerServlet?operator=dianzan&id="+id+"&qid=${QuesId}";
	} --%>

	function collect(){
		//登录请求：表单请求UserServlet
		location.href="<%=request.getContextPath()%>/QuestionServlet?operator=ifCollect&qid=${QuesId}";
       
		}

</script>
</head>
<body>
	<iframe src="head.jsp" scrolling="no" width="100%" height="65px"></iframe>
	<div class="main layui-clear">
		<div class="wrap">
			<div class="content detail">
				<div class="fly-panel detail-box">
					<h1>${sessionScope.ques.title}</h1>
					<div class="fly-tip fly-detail-hint" data-id="">
						<span class="fly-tip-stick">置顶帖</span><span class="jie-admin">
							<a href="">点击置顶</a>
						</span> <span class="layui-btn layui-btn-mini jie-admin"> <a
							href="">取消置顶</a>
						</span> <span class="jie-admin" type="del" style="margin-left: 20px;">
							<a onclick="delQuestion()">删除该帖</a>
						</span> </span>
						<div class="fly-list-hint">
							<i class="iconfont" title="回答">&#xe60c;</i> ${anum }
						</div>
					</div>
					<div class="detail-about">
						<a class="jie-user" href=""> <img src="res/images/uer.jpg"
							alt="头像"> <cite>${sessionScope.ques.user.username}<em>${sessionScope.ques.time}发布</em>
						</cite>
						</a>
						
						<div class="detail-hits" data-id="{{rows.id}}">
							<span class="layui-btn layui-btn-mini jie-admin"><a
								href="#">已完帖，无法编辑</a> </span> <span
								class="layui-btn layui-btn-mini jie-admin" type="collect"
								data-type="add"> <a id="collectPost" onclick="collect()">收藏</a>
							</span> <span
								class="layui-btn layui-btn-mini jie-admin  layui-btn-danger"
								type="collect" data-type="add"> <a>取消收藏</a>
							</span>

						</div>
					</div>
					<div class="detail-body photos" style="margin-bottom: 20px;">
						<p>${sessionScope.ques.content}</p>
					</div>
				</div>
				<div class="fly-panel detail-box" style="padding-top: 0;">
					<a name="comment"></a>
					<ul class="jieda photos" id="jieda">
						<c:forEach items="${Anslist}" var="userAns">
						<li data-id="12" class="jieda-daan"><a
							name="item-121212121212"></a>
							<div class="detail-about detail-about-reply">
								<a class="jie-user" href=""> <img src="res/images/uer.jpg"
									alt=""> <cite> <i>${userAns.user.username}</i> <!-- <em>(楼主)</em>
                  <em style="color:#5FB878">(管理员)</em> -->
								</cite>
								</a>
								<div class="detail-hits">
									<span>${userAns.time}</span>
								</div>
								<i class="iconfont icon-caina" title="最佳答案"></i>
							</div>
							<div class="detail-body jieda-body">
								<p>${userAns.content}</p>
							</div>
							<div class="jieda-reply">
								<span class="jieda-zan zanok" type="zan">
								<i class="iconfont icon-zan" id="${userAns.id}" onclick="support(${userAns.id})"></i><em id="support">${userAns.support}</em> </span>
								 <div class="jieda-admin">
									<!--  <a href="#" class="layui-btn  layui-btn-small">采纳</a> -->
									<span type="del">
<%--  ${(sessionScope.user.id==userAns.user.id)?"<a class='layui-btn layui-btn-danger layui-btn-small' onclick='delAnswer(${userAns.id})'>删除</a>":""}  
--%>								<a href='#' class='layui-btn layui-btn-danger layui-btn-small' onclick='delAnswer(${userAns.id})'>删除</a>  									</span> 
									<span class="jieda-accept" type="accept"></span> 
								</div>
							</div></li>
							</c:forEach>
					
					</ul>
					<span id="toName">@ 压缩(楼主)</span>
					<div class="layui-form layui-form-pane">
						<form action="<%=request.getContextPath() %>/AnswerServlet?operator=sendAnswer&qid=${QuesId}" method="post">
							<input type="hidden" name="pasteid" value="4028818764daf3aa0164daf5a3e40000"/>
							
							<div class="layui-form-item layui-form-text">
								<div class="layui-input-block">
									<div class="editor">
										<textarea id="content" name="content"
									style="width: 690px; height: 450px; visibility: hidden;"></textarea>
									</div>
								</div>
							</div>
							
							<div class="layui-form-item">
         						<input type="submit" class="layui-btn" value="发送回答">
        					</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="edge">
			<dl class="fly-panel fly-list-one">
				<dt class="fly-panel-title">最近热帖</dt>
				<dd>
					<a href="">使用 layui 秒搭后台大布局（基本结构）</a> <span><i
						class="iconfont">&#xe60b;</i> 6087</span>
				</dd>
				<dd>
					<a href="">Java实现LayIM后端的核心代码</a> <span><i class="iconfont">&#xe60b;</i>
						767</span>
				</dd>
				<dd>
					<a href="">使用 layui 秒搭后台大布局（基本结构）</a> <span><i
						class="iconfont">&#xe60b;</i> 6087</span>
				</dd>
				<dd>
					<a href="">Java实现LayIM后端的核心代码</a> <span><i class="iconfont">&#xe60b;</i>
						767</span>
				</dd>
				<dd>
					<a href="">使用 layui 秒搭后台大布局（基本结构）</a> <span><i
						class="iconfont">&#xe60b;</i> 6087</span>
				</dd>
				<dd>
					<a href="">Java实现LayIM后端的核心代码</a> <span><i class="iconfont">&#xe60b;</i>
						767</span>
				</dd>
				<dd>
					<a href="">使用 layui 秒搭后台大布局（基本结构）</a> <span><i
						class="iconfont">&#xe60b;</i> 6087</span>
				</dd>
				<dd>
					<a href="">Java实现LayIM后端的核心代码</a> <span><i class="iconfont">&#xe60b;</i>
						767</span>
				</dd>
			</dl>

			<dl class="fly-panel fly-list-one">
				<dt class="fly-panel-title">近期热议</dt>
				<dd>
					<a href="">使用 layui 秒搭后台大布局之基本结构</a> <span><i
						class="iconfont">&#xe60c;</i> 96</span>
				</dd>
				<dd>
					<a href="">使用 layui 秒搭后台大布局之基本结构</a> <span><i
						class="iconfont">&#xe60c;</i> 96</span>
				</dd>
				<dd>
					<a href="">使用 layui 秒搭后台大布局之基本结构</a> <span><i
						class="iconfont">&#xe60c;</i> 96</span>
				</dd>
				<dd>
					<a href="">使用 layui 秒搭后台大布局之基本结构</a> <span><i
						class="iconfont">&#xe60c;</i> 96</span>
				</dd>
				<dd>
					<a href="">使用 layui 秒搭后台大布局之基本结构</a> <span><i
						class="iconfont">&#xe60c;</i> 96</span>
				</dd>
				<dd>
					<a href="">使用 layui 秒搭后台大布局之基本结构</a> <span><i
						class="iconfont">&#xe60c;</i> 96</span>
				</dd>
				<dd>
					<a href="">使用 layui 秒搭后台大布局之基本结构</a> <span><i
						class="iconfont">&#xe60c;</i> 96</span>
				</dd>
				<dd>
					<a href="">使用 layui 秒搭后台大布局之基本结构</a> <span><i
						class="iconfont">&#xe60c;</i> 96</span>
				</dd>
			</dl>
		</div>
	</div>

	<script type="text/javascript" charset="utf-8" src="js/kindeditor.js"></script>
	<script type="text/javascript">
	function support(id){
		$.ajax({
			url:"<%=request.getContextPath() %>/AnswerServlet?operator=support",
			type:"get",
			data:"id="+id,
			dateType:"text",
			success:function(data){
				var result=$.parseJSON(data);
				$('#'+id).next().html(result);		
			}
		});
	}
	
		KE.show({
			id : 'content',
			resizeMode : 1,
			cssPath : './index.css',
			items : [ 'fontname', 'fontsize', 'textcolor', 'bgcolor', 'bold',
					'italic', 'underline', 'removeformat', 'justifyleft',
					'justifycenter', 'justifyright', 'insertorderedlist',
					'insertunorderedlist', 'emoticons', 'image', 'link' ]
		});
	</script>
	<script>
		layui.cache.page = '';
		layui.cache.user = {
			username : '游客',
			uid : -1,
			avatar : '../res/images/avatar/00.jpg',
			experience : 83,
			sex : '男'
		};
		layui.config({
			version : "2.0.0",
			base : '../res/mods/'
		}).extend({
			fly : 'index'
		}).use('fly');
	</script>
</body>
</html>