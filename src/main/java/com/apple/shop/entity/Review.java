package com.apple.shop.entity;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long Id;

private String comment;


     @ManyToOne(cascade = CascadeType.ALL)
    private Goods goods;

/*
    @ManyToOne(cascade = CascadeType.ALL)
    private CustomUser user;
*/

    public Review(String comment, Goods goods,CustomUser customUser) {
        this.comment = comment;
        this.goods = goods;

    }

    public Review() {
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }


}
