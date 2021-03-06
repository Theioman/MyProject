package com.wenda.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.alibaba.fastjson.JSON;
import com.wenda.domain.Collection;
import com.wenda.domain.Question;
import com.wenda.domain.Users;
import com.wenda.service.QuestionService;
import com.wenda.util.PageUtil;
/**
 * Servlet implementation class QuestionServlet
 */
@WebServlet("/QuestionServlet")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String operator=request.getParameter("operator");
		if("allQuestion".equals(operator)) {
			allQuestion(request,response);
		}else if("sendQuestion".equals(operator)){
			sendQuestion(request,response);
		}else if("toPage".equals(operator)) {
			toPage(request, response);
		}else if("myQuestion".equals(operator)) {
			myQuestion(request,response);
		}else if("delQuestion".equals(operator)){
			delQuestion(request,response);
		}else if("ifCollect".equals(operator)){
			ifCollect(request,response);
		}
	
	}
	/**
	 * 我的问题
	 * @param request
	 * @param response
	 */
	private void myQuestion(HttpServletRequest request, HttpServletResponse response) {
		try {
		QuestionService qs=QuestionService.getInstance();
		HttpSession session=request.getSession();
		String id=request.getParameter("uid");
		System.out.println(id);
		if(id=="undefined"||id.equals("undefined")) {
			String json=JSON.toJSONString("尚未登录");
			response.setContentType("text/plain;charset=utf-8");
			response.getWriter().println(json);
		}else {
			List<Question> lists=qs.findMyQuestion(Integer.parseInt(id));
			session.setAttribute("myquestion", lists);
			response.sendRedirect("index.jsp");
			System.out.println(lists);
		}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//查找问题是否已收藏

	private void ifCollect(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("进入ifCollection");
		Users user = (Users) request.getSession().getAttribute("user");
		int uid = user.getId();
		int qid = Integer.parseInt(request.getParameter("qid"));
		QuestionService qs = QuestionService.getInstance();
		Collection collection = new Collection(uid,qid);
		if(qs.ifCollect(collection)==true){
			System.out.println("已收藏了");
			try {
				request.getRequestDispatcher("detail.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{
			System.out.println("没有收藏");
			qs.collections(collection);
			try {
				request.getRequestDispatcher("detail.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

//	//收藏问题
//	private void collectQuestion(HttpServletRequest request,
//			HttpServletResponse response) {
//		// TODO Auto-generated method stub
//		System.out.println("进入collectQuestion");
//		Users user = (Users) request.getSession().getAttribute("user");
//		int uid = user.getId();
//		int qid = Integer.parseInt(request.getParameter("qid"));
//		QuestionService qs = QuestionService.getInstance();
//		Collection collection = new Collection(uid,qid);
//		qs.collections(collection);
//		
//	}
	/**
	 * 删除问题
	 * @param request
	 * @param response
	 */
	private void delQuestion(HttpServletRequest request,
		HttpServletResponse response) {
		String qid=request.getParameter("qid");
		QuestionService questionservice=QuestionService.getInstance();
		questionservice.delquestion(Integer.parseInt(qid));
		try {
			response.sendRedirect(request.getContextPath()+"/QuestionServlet?operator=allQuestion");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 发布问题
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void sendQuestion(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String reward=request.getParameter("reward");
		StringBuffer sb=ValiQuestion(title, content, reward);
		if(sb.length()>0){
			request.setAttribute("mess", sb.toString());
			request.getRequestDispatcher("add.jsp").forward(request, response);
		}else{
			HttpSession session=request.getSession();
			Users user=(Users) session.getAttribute("user");
			QuestionService questionservice=QuestionService.getInstance();
			questionservice.sendQuestion(title,content,Integer.parseInt(reward),user.getId());
			response.sendRedirect(request.getContextPath()+"/QuestionServlet?operator=allQuestion");
		}
	}
	/**
	 * 发布问题的表单验证
	 * @param title
	 * @param content
	 * @param reward
	 * @return
	 */
	private StringBuffer ValiQuestion(String title, String content, String reward) {
		StringBuffer sb=new StringBuffer();
		if(title==null||"".equals(title.trim())){
			sb.append("标题不能为空");
		}else{
			if(content==null||"".equals(content.trim())){
				sb.append("问题不能为空");
			}else{
				if(reward==null||"".equals(reward.trim())){
					sb.append("赏金不能为空");
				}
			}
		}
		return sb;
	}
	/**
	 * 查询所有问题
	 * @param request
	 * @param response
	 */
	private void allQuestion(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		QuestionService qs=QuestionService.getInstance();
		List<Question> list=qs.findAllQuestion();
		HttpSession session=request.getSession();
		session.setAttribute("lists", list);
		PageUtil page=new PageUtil(list, null, null);
		session.setAttribute("page_current", page.getPage_current());
		session.setAttribute("pageCount", page.getPageCount());
		session.setAttribute("allquestion", page.getSubList());
		session.setAttribute("is", "1");
		try {
			response.sendRedirect(request.getContextPath()+"/UserServlet?operator=showExperts");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 跳转页面
	 * @param req
	 * @param resp
	 */
	private void toPage(HttpServletRequest req, HttpServletResponse resp) {
		String pageCurrent=req.getParameter("pageCurrent");
		HttpSession session=req.getSession();
		List<Question> list=(List<Question>) session.getAttribute("lists");
		PageUtil page=new PageUtil(list, null, Integer.parseInt(pageCurrent));
		session.setAttribute("page_current", page.getPage_current());
		session.setAttribute("allquestion", page.getSubList());
		try {
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
}
