package com.apple.shop.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class GoodsInBasket implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Integer count;

    private String model;

    private Integer memory;
    private Integer price;

    @ManyToOne(fetch = FetchType.EAGER)
    private Goods goods;



@JsonIgnore
    @ManyToOne
    private Basket basket;


    public GoodsInBasket() {
    }

    public GoodsInBasket(Goods goods , Integer count,Integer memory,Integer price,Basket basket) {

    this.goods=goods;
    this.count=count;
    this.memory=memory;
    this.price=price;
    this.basket=basket;
    this.model=goods.getModel();


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setMemory(Integer memory) {
        this.memory=memory;
    }

    public Integer getMemory() {
        return memory;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsInBasket that = (GoodsInBasket) o;
        return Objects.equals(model, that.model) &&
                Objects.equals(memory, that.memory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, memory);
    }
}
