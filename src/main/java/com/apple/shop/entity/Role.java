package com.apple.shop.entity;

public enum Role {
    ADMIN,USER;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }



}
