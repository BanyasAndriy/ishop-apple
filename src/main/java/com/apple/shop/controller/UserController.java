package com.apple.shop.controller;


import com.apple.shop.dto.BasketGoodsDto;
import com.apple.shop.entity.*;
import com.apple.shop.service.BasketService;
import com.apple.shop.service.CategoryService;
import com.apple.shop.service.GoodsService;
import com.apple.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    UserService userService;


    @Autowired
    GoodsService goodsService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    BasketService basketService;

    @RequestMapping(value="/current_user_name",method= RequestMethod.POST)
    public       List<GoodsInBasket> getCurrentUserName(@RequestBody BasketGoodsDto goodsDto
                                              ){

        String[] allCounts = goodsDto.getCount().split(",");
        String[] allGoodsNames=goodsDto.getName().split(",");
        String[] allModels = goodsDto.getModel().split(",");
        String [] allMemories = goodsDto.getMemory().split(",");




        CustomUser customUser = userService.findByEmail(getEmailCurrentUser());
        List<Goods> goods = new ArrayList<>();

         Basket basket = customUser.getBasket();
    List<GoodsInBasket> goodsInBasket ;


        if (basket.getGoods()==null){
           goodsInBasket = new ArrayList<>();
        }else {
             goodsInBasket=basket.getGoods();
        }

        List<GoodsInBasket> newGoodsInBasket =new ArrayList<>();

        int count = (allCounts.length==1?allCounts.length :allCounts.length/2);


        for (int i = 0 ;i< count;i++) {

            goods.add(goodsService.findByModelAndMemory(allModels[i], Integer.parseInt(allMemories[i]), categoryService.getCategoryByString(allGoodsNames[i])));
        }


        for(int i =0;i<count;i++){

            newGoodsInBasket.add(new GoodsInBasket(goods.get(i),Integer.parseInt(allCounts[i]),Integer.parseInt(allMemories[i]),goods.get(i).getMemoryPrice().get(Integer.parseInt(allMemories[i]))*Integer.parseInt(allCounts[i]),basket));

        }



        for(int i = 0; i < goodsInBasket.size(); i++){
            for(int j = 0; j < newGoodsInBasket.size(); j++){
                if(goodsInBasket.get(i).equals(newGoodsInBasket.get(j))){
                    goodsInBasket.get(i).setCount(newGoodsInBasket.get(j).getCount());
                    goodsInBasket.get(i).setPrice(newGoodsInBasket.get(j).getPrice());
                    newGoodsInBasket.remove(j);
                }
            }
        }

goodsInBasket.addAll(newGoodsInBasket);


        for (int i = 0; i <goodsInBasket.size() ; i++) {

            basketService.updateBasket(goodsInBasket.get(i));

        }








        return goodsInBasket;

    }

    @RequestMapping(value="is-user-auth",method= RequestMethod.POST)
    public  String  isUserAuth(){


        CustomUser customUser = userService.findByEmail(getEmailCurrentUser());
        if (customUser==null){
            return "no";
        }else return "yes";

    }




    private String getEmailCurrentUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();


        return email;
    }






}
