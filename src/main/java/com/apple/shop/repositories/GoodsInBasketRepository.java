package com.apple.shop.repositories;

import com.apple.shop.entity.CustomUser;
import com.apple.shop.entity.GoodsInBasket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface GoodsInBasketRepository extends JpaRepository<GoodsInBasket,Long> {






}
