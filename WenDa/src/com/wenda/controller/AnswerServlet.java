package com.wenda.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.wenda.domain.Answer;
import com.wenda.domain.Question;
import com.wenda.domain.Users;
import com.wenda.service.AnswerService;
import com.wenda.service.QuestionService;

/**
 * Servlet implementation class AnswerServlet
 */
@WebServlet("/AnswerServlet")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String op=request.getParameter("operator");
		
		System.out.println("operator="+op);
		if("delAnswer".equals(op)){
			delAnswer(request,response);
		}else if("sendAnswer".equals(op)){
			sendAnswer(request,response);
		}else if("allAnswer".equals(op)){
			allAnswer(request,response);
		}else if("delAnswer".equals(op)){
			delAnswer(request,response);
		}else if("support".equals(op)) {
			support(request,response);
		}
	}
	/**
	 * 点赞功能
	 * @param request
	 * @param response
	 */
	private void support(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		AnswerService ans=AnswerService.getInstance();
		Integer num=ans.support(Integer.parseInt(id));
		String json=JSON.toJSONString(num);
		response.setContentType("text/plain;charset=utf-8");
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*private void dianzan(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		AnswerService ans=AnswerService.getInstance();
		ans.dianZan(Integer.parseInt(id));
		HttpSession session=request.getSession();
		String qid=request.getParameter("qid");
		ans.delAnswer(Integer.parseInt(id));
		List<Answer> list=ans.findAllAnswer(Integer.parseInt(qid));
		
		session.setAttribute("Anslist", list);
		request.setAttribute("QuesId",qid);
		request.getRequestDispatcher("detail.jsp").forward(request, response);
	}*/
	/**
	 * 查询所有评论
	 * @param request
	 * @param response
	 */
	private void allAnswer(HttpServletRequest request,
		HttpServletResponse response) {
		String id=request.getParameter("id");
		HttpSession session=request.getSession();
		System.out.println(id);
		session.getAttribute("allquestion");
		AnswerService ans=AnswerService.getInstance();
		List<Answer> list=ans.findAllAnswer(Integer.parseInt(id));
		System.out.println(list.size());
		for(Answer a:list) {
			if(a.getQuestion().getId()==Integer.parseInt(id)) {
				session.setAttribute("anum", a.getQuestion().getAnum());
			}
		}
		session.setAttribute("Anslist", list);
		System.out.println(list);
		QuestionService queservice=QuestionService.getInstance();
		Question que=queservice.findquestion(Integer.parseInt(id));
		session.setAttribute("ques", que);
		try {
			request.setAttribute("QuesId", id);
			request.getRequestDispatcher("detail.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 发表评论
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void sendAnswer(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String content=request.getParameter("content");
		if(content==null||"".equals(content)){
			request.setAttribute("mess", "请输入内容");
			request.getRequestDispatcher("detail.jsp").forward(request, response);
		}else{
			HttpSession session=request.getSession();
			String qid=request.getParameter("qid");
			AnswerService ans=AnswerService.getInstance();
			Users user=(Users) session.getAttribute("user");
			if(user==null) {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print("<script>alert('发表失败，请登录用户')</script>");
				response.sendRedirect("detail.jsp");
			}else {
				Integer anum=ans.sendAnswer(content,Integer.parseInt(qid),user.getId());
				List<Answer> list=ans.findAllAnswer(Integer.parseInt(qid));
				session.setAttribute("Anslist", list);
				request.setAttribute("QuesId",qid);
				session.setAttribute("anum", anum);
				session.setAttribute("is", null);
				request.getRequestDispatcher("detail.jsp").forward(request, response);
			}
		}
	}
	/**
	 * 删除评论
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delAnswer(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Users user = (Users) session.getAttribute("user");
		if(user!=null){
			String id = request.getParameter("id");
			String qid=request.getParameter("qid");
			AnswerService ans=AnswerService.getInstance();
			Integer anum=ans.delAnswer(Integer.parseInt(id),Integer.parseInt(qid));
			List<Answer> list=ans.findAllAnswer(Integer.parseInt(qid));
			session.setAttribute("anum", anum);
			session.setAttribute("Anslist", list);
			request.setAttribute("QuesId",qid);
			request.getRequestDispatcher("detail.jsp").forward(request, response);
			/*ans.delAnswer();*/
		}else {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('删除失败，请登录用户')</script>");
			response.sendRedirect("detail.jsp");
		}
		
	}
	
    
}
