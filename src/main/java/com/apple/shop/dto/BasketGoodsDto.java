package com.apple.shop.dto;

public class BasketGoodsDto {

    private String model;
    private String memory;
    private String count;
    private String name;




    public BasketGoodsDto(String model, String memory, String count, String name) {
        this.model = model;
        this.memory = memory;
        this.count = count;
        this.name = name;
    }

    public BasketGoodsDto() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}