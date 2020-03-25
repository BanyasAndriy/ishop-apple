package com.apple.shop.controller;


import com.apple.shop.dto.BasketGoodsDto;

import com.apple.shop.entity.*;
import com.apple.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
@RestController
public class BasketController {


    @Autowired
    BasketService basketService;

    @Autowired
    UserService userService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    GoodsInBasketService goodsInBasketService;


    @RequestMapping("/basket")
    ModelAndView showBasket(){

        ModelAndView res = new ModelAndView("basket");

        return res;

    }




    @RequestMapping(value="/get-goods-from-basket" , method = RequestMethod.POST)
    public @ResponseBody  List<GoodsInBasket> getGoodsFromBasket(){


        CustomUser customUser = userService.findByEmail(getEmailCurrentUser());
        if (customUser==null){
            return null;
        }
        Basket basket =  customUser.getBasket();

        List<GoodsInBasket> goodsInBasket = basket.getGoods();

        if(goodsInBasket.size()>1) {

            for (int i = 0; i < goodsInBasket.size(); ++i) {
                for (int j = i + 1; j < goodsInBasket.size(); ++j) {
                    if (goodsInBasket.get(i).equals(goodsInBasket.get(j))) {
                        goodsInBasket.get(i).setCount(goodsInBasket.get(i).getCount() + goodsInBasket.get(j).getCount());
                        goodsInBasket.remove(j);

                    }
                }

            }
        }

        return goodsInBasket;

    }







    @RequestMapping(value="/delete-from-basket" , method = RequestMethod.POST)
    public String getGoodsFromBasket(@RequestBody BasketGoodsDto basketGoodsDto){

String name = basketGoodsDto.getName();
String model = basketGoodsDto.getModel();
String memory=basketGoodsDto.getMemory();
String count = basketGoodsDto.getCount();


CustomUser customUser = userService.findByEmail(getEmailCurrentUser());


basketService.deleteGoodsFromBasket(model,name,memory,customUser);


        return "Ok";

    }



    @RequestMapping(value="/get-count-goods" , method = RequestMethod.POST)
    public String getCountOfGoods(){


        CustomUser customUser = userService.findByEmail(getEmailCurrentUser());
        Basket basket = customUser.getBasket();


        return String.valueOf(basket.getGoods().size());

    }






    private String  getEmailCurrentUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

  return email;
  }

}
