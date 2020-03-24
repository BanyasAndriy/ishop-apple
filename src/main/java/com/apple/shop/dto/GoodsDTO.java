package com.apple.shop.dto;

import com.apple.shop.entity.Goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GoodsDTO {
    private String name;
    private Long count;
    private Integer price;
    private String shortDescription;
    private String fullDescription;
    private String urlPhoto;
    private String model;


    private Set<String> allModels ;
    private Map<Integer,Integer> memoryAndPrice=new HashMap<>();
    private Set<Integer> allMemories;


    public GoodsDTO() {
    }

    public GoodsDTO(String name, Long count,  String shortDescription, String fullDescription, String urlPhoto, String model,Set<String> models,Integer price,Integer memory) {
        this.name = name;
        this.count = count;

        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.urlPhoto = urlPhoto;
        this.model = model;
        this.allModels=models;
        this.memoryAndPrice.put(memory,price);
    }


    public void from(Goods goods, Set<String> models, Set<Integer> allMemories){

        this.name=goods.getName();
        this.model=goods.getModel();
        this.fullDescription=goods.getFullDescription();
        this.shortDescription=goods.getShortDescription();
        this.count=goods.getCount();
        this.urlPhoto=goods.getUrlPhoto();
        this.memoryAndPrice=goods.getMemoryPrice();
        this.allModels=models;
        this.allMemories=allMemories;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
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


    public Set<String> getModels() {
        return allModels;
    }

    public void setModels(Set<String> models) {
        this.allModels = models;
    }


    public Set<String> getAllModels() {
        return allModels;
    }

    public void setAllModels(Set<String> allModels) {
        this.allModels = allModels;
    }

    public Map<Integer, Integer> getMemoryAndPrice() {
        return memoryAndPrice;
    }

    public void setMemoryAndPrice(Map<Integer, Integer> memoryAndPrice) {
        this.memoryAndPrice = memoryAndPrice;
    }


    public Set<Integer> getAllMemories() {
        return allMemories;
    }

    public void setAllMemories(Set<Integer> allMemories) {
        this.allMemories = allMemories;
    }


}
