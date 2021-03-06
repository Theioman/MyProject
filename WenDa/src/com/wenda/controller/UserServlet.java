package com.wenda.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wenda.domain.Users;
import com.wenda.service.UserService;
import com.wenda.util.Md5Utils;

/**
 * 
 * @author admin
 *
 */

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operator = request.getParameter("operator");
		
		if("login".equals(operator)){
			login(request,response);
		}else if("register".equals(operator)){
			register(request,response);
		}else if("showExperts".equals(operator)){
			showExperts(request,response);
		}
	}
	
	//显示专家排行榜的信息
	private void showExperts(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("jojojojo");
		UserService userService = UserService.getInstance();
		List<Users> list = userService.showExperts();
		HttpSession session=request.getSession();
		session.setAttribute("showExperts", list);
		try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 注册
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String readAgree=request.getParameter("readAgree");
		if(readAgree.equals("on")){
			String username_value = request.getParameter("username");// username是页面文本框的name的值
			String password_value = request.getParameter("password");
			String email = request.getParameter("email");
			String telphone =request.getParameter("phone");
			String code=request.getParameter("code");
			String password_com=request.getParameter("password_com");
			StringBuffer sb=Valiregister(username_value, password_value, email, telphone, code,
					password_com);
			if(sb.length()>0){
				request.setAttribute("mess", sb.toString());
				request.getRequestDispatcher("register.jsp").forward(request, response);			
			}else{
				HttpSession session=request.getSession();
				String right_code=(String) session.getAttribute("code");
				if(!right_code.equalsIgnoreCase(code)){
					request.setAttribute("mess", "验证码错误");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}else{
					UserService userService = UserService.getInstance();
				    Users user = new Users(username_value,password_value,email,telphone);
				    userService.register(user);
				    request.getRequestDispatcher("login.jsp").forward(request, response);
				} 
			}	
		}
	}
	/**
	 * 注册表单验证
	 * @param username_value
	 * @param password_value
	 * @param email
	 * @param telphone
	 * @param code
	 * @param password_com
	 * @return
	 */
	private StringBuffer Valiregister(String username_value, String password_value,
			String email, String telphone, String code, String password_com) {
		StringBuffer sb=new StringBuffer();
		if(username_value==null||"".equals(username_value.trim())){
			sb.append("用户名不能为空");
		}else{
			if(password_value==null||"".equals(password_value.trim())){
				sb.append("密码不能为空");
			}else{
				if(password_value==null||"".equals(password_value.trim())){
					sb.append("密码不能为空");
				}else{
					if(password_com==null||"".equals(password_com.trim())){
						sb.append("请确认密码");
					}else{
						if(!password_com.equals(password_value)){
							sb.append("两次密码不一致");
						}else{
							if(email==null||"".equals(email.trim())){
								sb.append("邮箱不能为空");
							}else{
								if(telphone==null||"".equals(telphone.trim())){
									sb.append("电话不能为空");
								}else{
									if(code==null||"".equals(code)){
										sb.append("请输入验证码");
									}
								}
							}
						}
					}
				}
			}
		}
		return sb;
	}
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String readAgree=request.getParameter("readAgree");
		System.out.println(readAgree.toString());
		if(readAgree.equals("on")){
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String code=request.getParameter("code");
			
			StringBuffer sb = ValiLogin(username, password, code);
			if(sb.length()>0){
				request.setAttribute("mess", sb.toString());
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else{
				HttpSession session=request.getSession();
				String right_code=(String) session.getAttribute("code");
				if(!right_code.equalsIgnoreCase(code)){
					request.setAttribute("mess", "验证码错误");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}else{
					Users user=UserService.getInstance().login(username,password);
					session.setAttribute("user", user);
					if(user==null){
						request.setAttribute("mess", "用户名密码错误");
						request.getRequestDispatcher("login.jsp").forward(request, response);
					}else{
						response.sendRedirect("index.jsp");
					}
				}
			}
		}else{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}
	/**
	 * 登录表单验证
	 * @param username
	 * @param password
	 * @param code
	 * @return
	 */
	private StringBuffer ValiLogin(String username, String password, String code) {
		StringBuffer sb=new StringBuffer();
		if(username==null||"".equals(username.trim())){
			sb.append("用户名不能为空");
		}else{
			if(password==null||"".equals(password.trim())){
				sb.append("密码不能为空");
			}else{
				if(code==null||"".equals(code.trim())){
					sb.append("验证码不能为空");
				}
			}
		}
		return sb;
	}
	

       
   

}
