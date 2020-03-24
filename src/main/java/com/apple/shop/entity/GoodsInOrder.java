package com.apple.shop.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class GoodsInOrder {

    @Id
    @GeneratedValue
    private Long id;
    private Integer count;
    private Integer memory;
    private Integer price;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-mm-yy")
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    private Goods goods;



    @JsonIgnore
    @ManyToOne
    private Order order;


    public GoodsInOrder() {
    }

    public GoodsInOrder(Integer count, Integer memory, Integer price, Date date, Goods goods, Order order) {
        this.count = count;
        this.memory = memory;
        this.price = price;
        this.date = date;
        this.goods = goods;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsInOrder that = (GoodsInOrder) o;
        return Objects.equals(memory, that.memory) &&
                Objects.equals(goods, that.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memory, goods);
    }
}
