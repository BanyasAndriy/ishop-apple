package com.apple.shop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import java.util.*;
@Entity
public class Goods {

    @Id
    @GeneratedValue
    private Long id;




    @OneToMany
    @JsonIgnore
    private List<GoodsInBasket> goodsInBaskets;

    @OneToMany
    @JsonIgnore
    private List<GoodsInBasket> goodsInOrder;



    private String name;
    private Long count;
   // private Long price;
    @Column( columnDefinition="TEXT")
    private String shortDescription;
    @Column( columnDefinition="TEXT")
    private String fullDescription;
    @Column( columnDefinition="TEXT")
    private String urlPhoto;
    private String model;
    @ElementCollection
    @CollectionTable(name = "memory_price",
            joinColumns = {@JoinColumn(name = "priceID", referencedColumnName = "id")})
    @MapKeyColumn(name = "memory")
    @Column(name = "price")
    private Map<Integer , Integer> memoryPrice = new TreeMap<>();



    @Enumerated(EnumType.STRING)
    private Category category;




    public Goods() {
    }

    public Goods(String name, Long count,  String shortDescription, String fullDescription, String urlPhoto, String model,Category category , Integer memory, Integer price) {
        this.name = name;
        this.count = count;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.urlPhoto = urlPhoto;
        this.model = model;
        this.category=category;
        this.memoryPrice.put(memory,price);
    }


    public Goods(String name, Long count,  String shortDescription, String fullDescription, String urlPhoto, String model, Order order, Category category) {
        this.name = name;
        this.count = count;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.urlPhoto = urlPhoto;
        this.model = model;

        this.category = category;
    }


    public List<GoodsInBasket> getGoodsInBaskets() {
        return goodsInBaskets;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setMemoryPrice(Map<Integer, Integer> memoryPrice) {
        this.memoryPrice = memoryPrice;
    }





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Map<Integer, Integer> getMemoryPrice() {
        return memoryPrice;
    }

    public void setMemoryAndPrice(Map<Integer, Integer> memoryAndPrice) {
        this.memoryPrice.putAll(memoryAndPrice);
    }

    public void setMemoryAndPrice(Integer memory,Integer price){
        this.memoryPrice.put(memory,price);
    }

    public List<GoodsInBasket> getGoods() {
        return goodsInBaskets;
    }

    public void setGoods(List<GoodsInBasket> goods) {
        this.goodsInBaskets = goods;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(name, goods.name) &&
                Objects.equals(shortDescription, goods.shortDescription) &&
                Objects.equals(fullDescription, goods.fullDescription) &&
                Objects.equals(model, goods.model) &&
                Objects.equals(memoryPrice, goods.memoryPrice) &&
                category == goods.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, shortDescription, fullDescription, model, memoryPrice, category);
    }
}
