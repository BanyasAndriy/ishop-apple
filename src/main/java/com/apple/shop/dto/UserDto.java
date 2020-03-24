package com.apple.shop.dto;

import com.apple.shop.entity.CustomUser;

public class UserDto {

    private String userName;
    private String mobileNumber;
    private String address;




    public void from(CustomUser customUser){
        this.userName = ""+customUser.getName()+" "+customUser.getFirstName();
        this.mobileNumber=customUser.getPhone();
        this.address=customUser.getAddress();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
