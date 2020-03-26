package com.apple.shop.controller;

import com.apple.shop.dto.OrderDto;
import com.apple.shop.entity.*;
import com.apple.shop.repositories.OrderRepository;
import com.apple.shop.service.*;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.*;
@RestController
public class OrderController {


    @Autowired
    UserService userService;

@Autowired
OrderService orderService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    BasketService basketService;


    @Autowired
    GoodsInBasketService goodsInBasketService;

    @RequestMapping("/order")
    public List<GoodsInOrder> orderPage(){



        CustomUser customUser = userService.findByEmail(getEmailCurrentUser());


List<GoodsInOrder> goodsInOrders = orderService.getGoodsInOrders(customUser);



        return goodsInOrders;
    }









    @RequestMapping("/create-order")
    public List<GoodsInOrder> createOrder(@RequestBody OrderDto orderDto){

         Order order = new Order();
         CustomUser currentUser = userService.findByEmail(getEmailCurrentUser());

         String[] allCounts = orderDto.getCount().split(",");
         String[] allGoodsNames=orderDto.getName().split(",");
         String[] allModels = orderDto.getModel().split(",");
         String [] allMemories = orderDto.getMemory().split(",");
         List<Goods> goods = new ArrayList<>();
List<GoodsInOrder> goodsInOrders = new ArrayList<>();



for (int i = 0 ;i<allModels.length;i++) {
    basketService.deleteGoodsFromBasket(allModels[i], allGoodsNames[i], allMemories[i], currentUser);
}

         for(int i =0;i<allCounts.length;i++){

 goods.add(goodsService.findByModelAndMemory(allModels[i],Integer.parseInt(allMemories[i]),categoryService.getCategoryByString(allGoodsNames[i])));
 goods.get(i).setCount(goods.get(i).getCount()-Integer.parseInt(allCounts[0]));


         }


        for(int i =0;i<allCounts.length;i++){

            goodsInOrders.add(new GoodsInOrder(Integer.parseInt(allCounts[i]),Integer.parseInt(allMemories[i]),goods.get(i).getMemoryPrice().get(Integer.parseInt(allMemories[i]))*Integer.parseInt(allCounts[i]),new Date(),goods.get(i),order));


        }

        for(int i=0;i<goodsInOrders.size();++i)
        {
            for(int j=i+1;j<goodsInOrders.size();++j)
            {
                if (goodsInOrders.get(i).equals(goodsInOrders.get(j))){
                    goodsInOrders.get(i).setCount(goodsInOrders.get(i).getCount()+goodsInOrders.get(j).getCount());
                    goodsInOrders.remove(j);

                }
            }

        }
        order.setCalendar(new Date());
        order.setCustomUser(currentUser);
        order.setGoodsInOrder(goodsInOrders);

        orderService.createOrder(order);

        for (int i = 0; i <goodsInOrders.size() ; i++) {

            orderService.updateOrder(goodsInOrders.get(i));

        }

         currentUser.setPhone(orderDto.getPhone());
         currentUser.setAddress(orderDto.getAddress());


         orderService.sendOrderToEmail(currentUser,goodsInOrders);

       /* Basket basket = currentUser.getBasket();
        List<GoodsInBasket> goodsInBasket=basket.getGoods();

         List<GoodsInOrder> allGoodsInOrders = orderService.getGoodsInOrders(currentUser);

*/



        return goodsInOrders;
    }










    private String getEmailCurrentUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();


        return email;
    }

}
