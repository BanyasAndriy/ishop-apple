package com.apple.shop.repositories;

import com.apple.shop.entity.Category;
import com.apple.shop.entity.Goods;
import com.apple.shop.entity.Order;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
public interface GoodsRepository extends JpaRepository<Goods,Long> {




    Goods findTopByModelAndCategoryAndNameIgnoreCase(String model, Category category, String name);


    List<Goods> findByModelAndCategory(String model,Category category);

    List<Goods> findByModel(String model);

    List<Goods> findAll();


    @Query("select g from Goods g where g.model Like '%:someDescription%' ")
    List<Goods> findBySomeDescription(@Param("someDescription") String someDescription);


    List<Goods> findDistinctByModelContains(String model);
    List<Goods> findDistinctByCategoryAndModelStartingWith(Category category , String model);




}


