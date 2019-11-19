package com.book.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.book.domain.Order;
import com.book.domain.Users;
import com.book.service.CartService;
import com.book.service.OrderService;
import com.book.service.UsersSiteService;
import com.book.service.vo.Common;


@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	public UsersSiteService usersSiteService;
	
	/**
	 * 根据页数和用户id分页查询订单信息并跳转到订单页面显示
	 * @param pagenum
	 * @param uid
	 * @return
	 */
	@RequestMapping("toOrder.do")
	public ModelAndView findAllOrder(Integer pagenum,Integer uid) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("user_center_order");
		if(uid==null||"".equals(uid)) {
			return mv;
		}
		if(pagenum==null||pagenum<=0) {
			pagenum=1;
		}
		int pageCount=orderService.findOrdersCount(uid);
		int page=pageCount/4;
		if(pageCount%4!=0||pageCount==0)page+=1;
		if(pagenum>page) {
			pagenum=page;
		}
		List<Order> list=orderService.findAllOrder(pagenum,uid);
		mv.addObject("page", page);
		mv.addObject("pagenum", pagenum);
		mv.addObject("orders", list);	
		return mv;
	}
	
	/**
	 * 
	*<p>Title: toPlaceOrder</p>
	*<p>Description: </p>
	　 * @param session
	　 * @param array
	　 * @return
	 */
	@RequestMapping("toPlaceOrder.do")
	public ModelAndView toPlaceOrder(HttpSession session,@RequestParam("array") String array){
		ModelAndView mav=new ModelAndView();
		Users user=(Users) session.getAttribute("user");
		System.out.println(array);
		Common common=new Common();
		if(user!=null){
			if(array!=null&&!array.equals("")){
				String[] ids=array.split(",");
				Integer[] iids=new Integer[ids.length];
				for(int i=0;i<iids.length;i++){
					iids[i]=Integer.parseInt(ids[i]);
				}
				System.out.println(ids.length);
				if(ids.length!=0){
					common=cartService.selectOfId(iids);
					List<Common> commons=cartService.selectDetailOfId(iids);
					mav.addObject("pn", common);
					mav.addObject("Commons", commons);
				}
				mav.addObject("info", usersSiteService.findUsersInfoById(user.getId()));
			}
			
		}
		mav.setViewName("place_order");
		return mav;
	}
	
	/**
	 * 
	*<p>Title: addOrder</p>
	*<p>Description: </p>
	　 * @param uid
	　 * @param totalprice
	　 * @param bnum
	　 * @param bid
	　 * @return
	 */
	@RequestMapping("addOrder.do")
	public String addOrder(Integer uid,Double totalprice,Integer[] bnum,Integer[] bid){
		List<Common> commons=new ArrayList<>();
		Common common = new Common();
		common.setUid(uid);
		common.setOrderid(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))+common.getUid());
		common.setTotalprice(totalprice);
		orderService.addOrder(common);
		for (int i = 0; i < bid.length; i++) {
			Common com=new Common();
			com.setBid(bid[i]);
			com.setBnum(bnum[i]);
			com.setOid(common.getId());
			commons.add(com);
		}
		System.out.println(commons.toString());
		orderService.addOrderDetail(commons);
		List<Integer> list=new ArrayList<>();
		Collections.addAll(list, bid);
		System.out.println(list.toString());
		orderService.deleteCartDetail(list);
		return "redirect:toIndex.do";
	}
	/**
	 * 点击立即购买跳转到订单页面
	 */
	@RequestMapping("buynow")
	public ModelAndView toBuy(Integer bid,Integer bnum) {
		ModelAndView mv=new ModelAndView();
		Common common=orderService.findInfoByid(bid);
		common.setBnum(bnum);
		common.setDetailnum(bnum);
		Double bprice=Double.parseDouble(common.getBprice());
		common.setTotalprice(bprice);
		List<Common> list=new ArrayList<>();
		list.add(common);
		mv.addObject("pn", common);
		mv.addObject("Commons", list);
		mv.setViewName("place_order");
		return mv;
	}
}
