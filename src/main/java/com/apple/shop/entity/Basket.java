package com.apple.shop.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Basket {

@Id
@GeneratedValue
private Long idBasket;



/*
@OneToMany(cascade = CascadeType.ALL , mappedBy = "basket")
List<Goods> goods = new ArrayList<>();
*/

    @OneToMany( mappedBy = "basket")
    List<GoodsInBasket> goods = new ArrayList<>();




    public Basket(List<GoodsInBasket> goods) {
        this.goods = goods;
    }

    public Basket() {

    }

    @OneToOne(mappedBy = "basket")
    private CustomUser customUser;


    public Long getIdBasket() {
        return idBasket;
    }

    public void setIdBasket(Long idBasket) {
        this.idBasket = idBasket;
    }

    public List<GoodsInBasket> getGoods() {
        return goods;
    }

    public void setGoods(GoodsInBasket goods) {
        this.goods.add(goods);
    }

    public void setGoods(List<GoodsInBasket> goods) {
        this.goods = goods;
    }

    public CustomUser getCustomUser() {
        return customUser;
    }

    public void setCustomUser(CustomUser customUser) {
        this.customUser = customUser;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "idBasket=" + idBasket +
                ", goods=" + goods +
                ", customUser=" + customUser +
                '}';
    }
}
