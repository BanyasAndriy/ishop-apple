package com.apple.shop.service;


import com.apple.shop.entity.CustomUser;
import com.apple.shop.entity.GoodsInOrder;
import com.apple.shop.entity.Order;
import com.apple.shop.repositories.GoodsInOrderRepository;
import com.apple.shop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service
public class OrderService {


    @Autowired
    OrderRepository orderRepository;


    @Autowired
    MailSender mailSender;

@Autowired
    GoodsInOrderRepository goodsInOrderRepository;


    @Transactional
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }



    @Transactional
    public boolean createOrder(Order order){


        orderRepository.save(order);

        return true;

    }


@Transactional
    public void sendOrderToEmail(CustomUser customUser , List<GoodsInOrder> goodsInOrders  ){

String setGoods=" ";
    for (GoodsInOrder goods:goodsInOrders
         ) {
      setGoods=setGoods+""+goods.getGoods().getName()+" "+goods.getGoods().getModel()+" " +goods.getMemory() +"~~Count: "+goods.getCount()+"\n";

    }


        String message="New order!\n Customer : "+customUser.getFirstName()+" "+customUser.getName()+" "+customUser.getSurName()
                +"\n Address : " +customUser.getAddress() +"\n Phone : "+customUser.getPhone() +
                "\n Products : + \n" + setGoods;


        mailSender.sendCreatedOrder(message);
    }




    @Transactional
    public List<Order> getOrdersByUser(CustomUser customUser){


      return   orderRepository.getOrdersByCustomUser(customUser);



    }

    @Transactional
    public boolean updateOrder(GoodsInOrder goodsInOrder){


      goodsInOrderRepository.save(goodsInOrder);

        return true;

    }


@Transactional
    public List<GoodsInOrder> getGoodsInOrders(CustomUser customUser) {

        List<Order> orders = getOrdersByUser(customUser);
List<GoodsInOrder> goodsInOrders = new ArrayList<>();
        for (Order order: orders
        ) {

            goodsInOrders.addAll(order.getGoodsInOrder());
        }

return goodsInOrders;

    }
}
