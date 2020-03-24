package com.apple.shop.service;


import com.apple.shop.entity.CustomUser;
import com.apple.shop.entity.GoodsInBasket;
import com.apple.shop.repositories.GoodsInBasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class GoodsInBasketService {


    @Autowired
    GoodsInBasketRepository goodsInBasketRepository;


    public void deleteGoodsFromBasket(GoodsInBasket basketGoods) {

        goodsInBasketRepository.delete(basketGoods);
    }
}
