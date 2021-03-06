package com.book.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;







import com.book.domain.Book;
import com.book.domain.Cart;

import com.book.domain.Users;
import com.book.service.CartService;
import com.book.service.vo.CartCommon;
import com.book.service.vo.Common;
@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	

	/*@RequestMapping("findAllCartDetail.do")
	public ModelAndView findAllCartDetail(Integer pagenum,HttpSession session){
		if(pagenum==0||pagenum<0){
			pagenum=1;
		}
		ModelAndView mav=new ModelAndView();
		Users user=(Users) session.getAttribute("user");
		int page=cartService.queryCartByid(user.getId()).size()/5;
		if(page%5>0){
			page+=1;
		}
		if(pagenum>page){
			pagenum=page;
		}
		List<Common> list=cartService.findAllCartDetail(pagenum);
		mav.addObject("pagenum", pagenum);
		mav.addObject("page", page);
		mav.addObject("CartDetails", list);
		mav.setViewName("cart");
		return mav;
	}*/


	/**
	 * 跳转到购物车页面并查询显示购物车数据
	 * @param session
	 * @return
	 */

	@RequestMapping("toCart.do")
	public ModelAndView toCart(HttpSession session){
		Users user=(Users) session.getAttribute("user");
		ModelAndView mav=new ModelAndView();
		if(user==null){
			mav.setViewName("cart");
		}else{
			List<Common> Commons=cartService.queryCartByid(user.getId());
			Double totalPrice=cartService.queryTotalPrice(user.getId());
			Integer detailNum=cartService.queryDetailNum(user.getId());
			mav.addObject("Commons", Commons);
			mav.addObject("totalPrice", totalPrice);
			mav.addObject("detailNum", detailNum);
			mav.setViewName("cart");
		}
		return mav;
	}
	
	
	

	/*@RequestMapping("queryCartByBname.do")
	@ResponseBody
	public List<Common> queryCartByBname(Common common){
		System.out.println(common.getBname());
		List<Common> commons=cartService.queryCartByBname(common);
		System.out.println(commons.size());
		return commons;
	}*/
	

	/**
	 * 删除购物车的指定商品
	 * @param session
	 * @param id
	 * @return
	 */

	@RequestMapping("delCartDetail.do")
	@ResponseBody
	public List<Common> delCartDetail(HttpSession session,Integer id){
		System.out.println(id);
		Users user=(Users) session.getAttribute("user");
		List<Common> Commons=cartService.queryCartByid(user.getId());
		cartService.delCartDetail(id);
		return Commons;
	}
	/**
	 *查询购物车的总价和商品数
	 * @param session
	 * @return
	 */
	@RequestMapping("queryPriceAndNum.do")
	@ResponseBody
	public Common queryPriceAndNum(HttpSession session){
		Users user=(Users) session.getAttribute("user");
		Common common=cartService.queryPriceAndNum(user.getId());
		return common;
	}

	
	@RequestMapping("addCartBnum.do")
	@ResponseBody
	public Common addCartBnum(Common common){
		if(common.getBnum()>=100){
			common.setBnum(100);
			cartService.updateTest(common);
		}else{
			cartService.addCartBnum(common.getId());
		}
		Common common2=cartService.queryCartDetailById(common.getId());
		return common2;
	}
	
	@RequestMapping("decreaseCartBnum.do")
	@ResponseBody
	public Common decreaseCartBnum(Common common){
		if(common.getBnum()<=0){
			common.setBnum(0);
			cartService.updateTest(common);
		}else{
			cartService.decreaseCartBnum(common.getId());
		}
		Common common2=cartService.queryCartDetailById(common.getId());
		System.out.println(common2.getBbprice());
		return common2;
	}
	
	@RequestMapping("updateTest.do")
	@ResponseBody
	public Common updateTest(Common common){
		if(common.getBnum()>100){
			common.setBnum(100);
		}else if(common.getBnum()<=0){
			common.setBnum(0);
		}
		cartService.updateTest(common);
		Common com=cartService.queryCartDetailById(common.getId());
		return com;
	}

	@RequestMapping("selectOfId.do")
	@ResponseBody
	public Common selectOfId(@RequestBody Integer[] ids){
		Common common=new Common();
		if(ids.length!=0){
			System.out.println(Arrays.toString(ids));
			common=cartService.selectOfId(ids);
		}else{
			common.setSbprice(0.0);
			common.setDetailnum(0);
		}
		return common;
	}
	

	/**
	 * 加入购物车
	 */
	@ResponseBody
	@RequestMapping("addToCart.do")
	public String addToCart(CartCommon common,HttpSession session) {
		if(common.getUid()==-1) {
			return "{\"result\":\"请先登录\"}";
		}
		cartService.addToCart(common);
		Common com=cartService.queryPriceAndNum(common.getUid());
		Integer num=com.getDetailnum();
		session.setAttribute("common", com);
		return "{\"result\":\"加入购物车成功\",\"detailnum\":\""+num+"\"}"; 
	}

}
