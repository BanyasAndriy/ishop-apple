package com.apple.shop.entity;

public enum Category {

    PHONE,LAPTOP,WATCH,AIRPODS;


    @Override
    public String toString() {
        return name();
    }

}
