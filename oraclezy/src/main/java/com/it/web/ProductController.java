package com.it.web;

import com.it.domain.Product;
import com.it.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping(path = "/findAll")
    public ModelAndView findAll() throws  Exception{
        ModelAndView mv=new ModelAndView();
        List<Product> all = productService.findAll();
        mv.addObject("list",all);
        mv.setViewName("pages/product-list");
        return mv;
    }
    @RequestMapping(path = "/save")
    public String save(Product product)throws Exception{
        productService.save(product);
        return "redirect:/findAll";
    }
    @RequestMapping(path = "/delete")
    public String delete(Integer id)throws Exception{
        productService.delete(id);
        return "redirect:/findAll";
    }
    @RequestMapping(path = "/update")
    public String update(Product product)throws Exception{
        productService.update(product);
        return "redirect:/findAll";
    }

}
