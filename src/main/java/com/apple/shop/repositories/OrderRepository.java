package com.apple.shop.repositories;


import com.apple.shop.entity.CustomUser;
import com.apple.shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface OrderRepository extends JpaRepository<Order,Long> {


    List<Order> getOrdersByCustomUser(CustomUser customUser);

}
