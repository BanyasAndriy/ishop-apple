package com.apple.shop.controller;


import com.apple.shop.entity.Category;
import com.apple.shop.entity.Goods;
import com.apple.shop.service.CategoryService;
import com.apple.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.net.Inet4Address;


@Controller
public class GoodsController {


    @Autowired
   private GoodsService goodsService;

    @Autowired
   private CategoryService categoryService;

    @RequestMapping("/add_goods")
    String addGoodsPage(){
        return "add_goods";
    }

    @RequestMapping("/goods_page")
    String goodsPage(){
        return "goodsPage";
    }



    @RequestMapping("/page_goods")
    ModelAndView pageGoods()
    {
        ModelAndView modelAndView = new ModelAndView("main_page_goods");
        return modelAndView;
    }



    @RequestMapping("/save_goods")
    ModelAndView save(@RequestParam("name") String name,
                      @RequestParam("count")Long count,
                      @RequestParam("category")String categoryName,
                      @RequestParam("model")String model,
                      @RequestParam("shortDescription") String shortDescription,
                      @RequestParam("fullDescription") String description,
                      @RequestParam("price") Integer price,
                      @RequestParam("url") String urlPhoto,
                      @RequestParam(value = "memory",required = false)Integer memory){


        if (memory==null){
            memory=1;
        }

        ModelAndView modelAndView=new ModelAndView("add_goods");
        Goods goods = new Goods(name,count,shortDescription,description,urlPhoto,model,categoryService.getCategoryByString(categoryName),memory,price );


        goodsService.saveGoods(goods);
        modelAndView.addObject("message","Goods was added");

        return modelAndView;

    }



    }






