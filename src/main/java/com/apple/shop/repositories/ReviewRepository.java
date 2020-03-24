package com.apple.shop.repositories;

import com.apple.shop.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Basket,Long> {

}
