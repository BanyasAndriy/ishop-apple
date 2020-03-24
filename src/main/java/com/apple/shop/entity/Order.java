package com.apple.shop.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue
    private Long idOrder;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-mm-yy HH:mm")
    private Date calendar;




@OneToMany(cascade = CascadeType.ALL)
List<GoodsInOrder> goodsInOrder = new ArrayList<>();




    @ManyToOne(cascade = CascadeType.ALL)
    private CustomUser customUser;



    public Order() {
    }



    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }



    public CustomUser getCustomUser() {
        return customUser;
    }

    public void setCustomUser(CustomUser customUser) {
        this.customUser = customUser;
    }

    public Date getCalendar() {
        return calendar;
    }

    public void setCalendar(Date calendar) {
        this.calendar = calendar;
    }

    public List<GoodsInOrder> getGoodsInOrder() {
        return goodsInOrder;
    }

    public void setGoodsInOrder(List<GoodsInOrder> goodsInOrder) {
        this.goodsInOrder = goodsInOrder;
    }



}
