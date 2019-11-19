<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/index.css">
</head>
<% 
	Object obj=session.getAttribute("is");
	String is=null;
	if(obj!=null){
		is=obj.toString();
	}
	if(is==null&&!"1".equals(is)){
	response.sendRedirect(request.getContextPath()+"/QuestionServlet?operator=allQuestion");
	}
%>

<script type="text/javascript">	
	function todetail(id) {
		<%-- window.navigate("<%=request.getContextPath()%>/AnswerServlet?operator=allAnswer"); --%>
		location.href="<%=request.getContextPath()%>/AnswerServlet?operator=allAnswer&id="+id;
	}
</script>

<body>

<div class="dvhead">
	<div class="dvlogo"><a href="index.jsp">你问我答</a></div>
	<div class="dvsearch">10秒钟注册账号，找到你的同学</div>
	<div class="dvreg">
		${sessionScope.user==null?"已有账号，立即&nbsp;<a href='login.jsp'>登录</a>":sessionScope.user.username }
	</div>
</div>	
<div class="dvContent">
	<div class="dvquesleft">

		<div class="dvqstitle">
			<image class="imgbean" src="images/bean.jpg">
			<span class="qsTitle">问答</span>
			<span class="back"><ab  href="">《《返回上一页</a></span>
		</div>
		<div class="dvtabhead">
			<div class="tabheads tabcurrent">全部问题</div>
			<div class="tabheads" onclick="myQuestion(${sessionScope.user.id})">我的问题</div>
			<div class="tabheads">关注问题</div>
			<div class="tabheads">问题标签</div>
		</div>
		<div class="tabContent">
			<div class="dvtags">
				<a class="curenttag">待解决</a><span class="line"></span><a>高分</a><span
					class="line"></span><a>新回答</a><span class="line"></span><a>已解决</a>
			</div>
			<div class="tab">
				<c:forEach items="${sessionScope.allquestion}" var="question">
				<div class="dvques">
					<div class="quesCount">
						<div class="count">${question.anum }</div>
						<div class="ques">回答数</div>
					</div>
					<div class="quesContent">
						<div class="quesTitle">
							${question.reward }
							<image src="images/bean.jpg" class="bean"> 
							<a class="spanques" onclick="todetail('${question.id}')">${question.title}</a>
						</div>
						<div class="qContent">${question.content }</div>
						<div class="tags">
						<span class="tag">excel</span>
						<span class="tag">程序</span>
						</div>
						<div class="quesUser">
							<image src="images/0.gif" class="imguser" />
							<div class="userName">
								${question.users.username }
								<div class="liulan">浏览(${question.visit }) 30分钟前</div>
							</div>

						</div>
					</div>
				</div>
				</c:forEach>
				<div style="clear:both;margin:30px 0px 20px 20px">
	 >> 
            <% Object pageCount=session.getAttribute("pageCount"); 
            	Integer count=0;
            	if(pageCount!=null){
            		count=(Integer)pageCount;
            	}
            	for(int i=1;i<=count;i++){
            %>
            <a href="#" onclick="toPage(<%=i%>)"><%=i %></a>
            <%} %>
             &lt;&lt;
    </div>
			</div>
			<div class="tab hidden" id="myquestion">
				<c:forEach items="${sessionScope.myquestion}" var="q">
				<div class="dvques">
					<div class="quesCount">
						<div class="count">${q.anum }</div>
						<div class="ques">回答数</div>
					</div>
					<div class="quesContent">
						<div class="quesTitle">
							${q.reward }
							<image src="images/bean.jpg" class="bean"> <span
								class="spanques">${q.title }</span>
						</div>
						<div class="qContent">${q.content }</div>
						<div class="tags">
						<span class="tag">excel</span>
						<span class="tag">程序</span>
						</div>
						<div class="quesUser">
							<image src="images/0.gif" class="imguser" />
							<div class="userName">
								${q.users.username }
								<div class="liulan">浏览(${q.visit }) 30分钟前</div>
							</div>

						</div>
					</div>
				</div>
				</c:forEach>
			</div>
			<div class="tab hidden">3</div>
			<div class="tab hidden">4</div>
		</div>
			
	</div>
	<div class="dvquesright">
		<div>
			<buton class="btnques" onclick="location.href='add.jsp'">提个问题</buton>
		</div>
		<div class="dvorder">
			<div class="orderTitle">专家排行榜</div>
			<c:forEach items="${showExperts}" var="se">			
			<div class="users">
				<image class="userface" src="${se.image}" />
				<div class="dvuser">
					<div class="userTitle">${se.username}</div>
					<div class="userdeital">${se.grdae}  豆：${se.money}
					</div>
				</div>
			</div>
            </c:forEach>
		</div>

	</div>

	 	
</div>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function myQuestion(id){
	$.ajax({
		url:"<%=request.getContextPath() %>/QuestionServlet?operator=myQuestion",
		type:"get",
		data:"uid="+id,
		dateType:"text",
		success:function(data){
			var result=$.parseJSON(data);
			$("#myquestion").html(result);		
		}
	});
	
}
	$(function()
	{
		$(".tabheads").click(function()
		{
			$(".tabheads").removeClass("tabcurrent").eq($(this).index()).addClass("tabcurrent");
			$(".tab").hide().eq($(this).index()).show();
		});
	});
	function toPage(i){
		location.href="<%=request.getContextPath() %>/QuestionServlet?operator=toPage&pageCurrent="+i;
	}
	
</script>
</body>
</html>

