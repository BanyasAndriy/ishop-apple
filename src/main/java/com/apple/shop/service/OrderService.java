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
