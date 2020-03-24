package com.apple.shop.repositories;

import com.apple.shop.entity.GoodsInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsInOrderRepository  extends JpaRepository<GoodsInOrder,Long> {
}
