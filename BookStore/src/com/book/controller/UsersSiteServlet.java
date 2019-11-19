package com.book.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.book.domain.Users;
import com.book.domain.UsersSite;
import com.book.service.UsersSiteService;

@Controller
public class UsersSiteServlet {
	
	@Autowired
	public UsersSiteService usersSiteService;

	/**
	 * 查询用户地址
	 * @param session
	 * @return
	 */
	@RequestMapping("queryUsersSite.do")
	public ModelAndView queryUserSite(HttpSession session){
		Users user=(Users) session.getAttribute("user");
		ModelAndView mav=new ModelAndView();
		if(user==null) {
			mav.setViewName("user_center_site");
			return mav;
		}
		if(usersSiteService.findUsersSiteById(user.getId()) == null){
			mav.addObject("UsesSiteStatus","addUsersSite.do");
			mav.addObject("bt_status","新增地址");
		}else{
			mav.addObject("UsesSiteStatus","updateUsersSite.do");
			mav.addObject("bt_status","修改地址");
		}
/*		session.setAttribute("usersSite",usersSiteService.findUsersSiteById(user.getId()));
*/	    mav.addObject("usersSite",usersSiteService.findUsersSiteById(user.getId()));
		mav.setViewName("user_center_site");
		return mav;
	}
	
	/**
	 * 修改用户地址
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("updateUsersSite.do")
	public String updateUsersSite(HttpSession session,HttpServletRequest request,UsersSite usersSite){
		Users user=(Users) session.getAttribute("user");
		usersSite.setUid(user.getId());
		usersSiteService.updateUsersSiteById(usersSite);
		return "redirect:queryUsersSite.do";
	}
	
	
	@RequestMapping("addUsersSite.do")
	public String addUsersSite(HttpSession session,HttpServletRequest request,UsersSite usersSite){
		Users user = (Users) session.getAttribute("user");
		usersSite.setUid(user.getId());
		usersSiteService.addUsersSiteById(usersSite);
		return  "redirect:queryUsersSite.do";
		
	}
	/**
	 * 查询用户个人信息
	 * @param session
	 * @return
	 */
	@RequestMapping("queryUsersInfo.do")
	public String queryUsersInfo(HttpSession session){
		ModelAndView mav = new ModelAndView();
		Users user=(Users) session.getAttribute("user");
		if(user==null) {
			return "user_center_info";
		}
		/*		mav.addObject("info",usersSiteService.findUsersInfoById(user.getId()));
*/		System.out.println(user.getId());
		session.setAttribute("info", usersSiteService.findUsersInfoById(user.getId()));
  	    return "redirect:selectRecent.do";
/*      mav.setViewName("user_center_info");	
        return mav;*/
	
	}
	
	

}