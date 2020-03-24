package com.apple.shop.repositories;

import com.apple.shop.entity.Basket;
import com.apple.shop.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket,Long> {

    Basket findBasketsByCustomUser(CustomUser customUser );



}
