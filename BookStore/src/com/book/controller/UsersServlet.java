package com.book.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.book.domain.Users;
import com.book.service.UsersService;

@Controller
public class UsersServlet {

	@Autowired
	public UsersService usersService;
	/**
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping("toLogin.do")
	public String toLogin(HttpSession session){
		session.invalidate();
		return "login";
	}

	/**
	 * 注册  判断用户是否存在，不存在则注册用户并跳转到登录页面，存在则跳转回注册页面并返回错误信‘用户已存在’
	 * @param user
	 * @return
	 */
	@RequestMapping("register.do")
	public ModelAndView register(Users user) {
		ModelAndView mv=new ModelAndView();
		System.out.println(user.getUsername());
			if(usersService.register(user)) {
			mv.addObject("username", user.getUsername());
			mv.setViewName("login");
			return mv;
			}else {
				mv.addObject("error", "用户已存在");
				mv.setViewName("register");
				return mv;
			}
	}
	
	/**
	 * 跳转到注册页面
	 * @return
	 */
	@RequestMapping("toRegister.do")
	public String toRegister() {
		return "register";
	}
	/**
	 * 登录  判断用户是否存在，存在则登录并判断是否勾选自动登录，勾选则向cookie添加用户名和密码，七天内跳转到登录页面会自动执行登录，不存在则回到登录页面
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("login.do")
	public ModelAndView login(Users user,HttpServletRequest request, HttpServletResponse response){
		Users user1 = usersService.login(user);
		String autoLogin=request.getParameter("autoLogin");
		System.out.println(autoLogin);
		ModelAndView mav=new ModelAndView();
		if(user1!=null){
			if("on".equals(autoLogin)){
				writeCookie(user, request, response);
			}
			request.getSession().setAttribute("user", user1);
			mav.setViewName("redirect:toIndex.do");

		}else{
			mav.setViewName("login");
		}
		return mav;
	}
	/**
	 * 用户退出登录
	 * @param request
	 * @param response
	 */
	@RequestMapping("exit.do")
	public String exit(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		session.invalidate();
		Users u=new Users();
		u.setUsername("");
		u.setPassword("");
		writeCookie(u, request, response);
		return "redirect:login.do";
	}
	
	/**
	 * 添加Cookie信息
	 * @param user
	 * @param request
	 * @param response
	 */
	public void writeCookie(Users user,HttpServletRequest request, HttpServletResponse response){
		Cookie u_cookie=new Cookie("username", user.getUsername());
		u_cookie.setMaxAge(60*60*24*7);
		u_cookie.setPath(request.getContextPath());
		
		Cookie p_cookie=new Cookie("password", user.getPassword());
		p_cookie.setMaxAge(60*60*24*7);
		p_cookie.setPath(request.getContextPath());
		response.addCookie(u_cookie);
		response.addCookie(p_cookie);
	}
	/**
	 * 跳转到用户中心
	 * @return
	 */
	@RequestMapping("toUserCenter.do")
	public String toUserCenter() {
		return "user_center_info";
	}
	
}
