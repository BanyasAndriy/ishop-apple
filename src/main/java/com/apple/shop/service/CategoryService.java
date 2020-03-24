package com.apple.shop.service;

import com.apple.shop.entity.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {



    public Category getCategoryByString(String categoryName){

      Category category=null;
        if (categoryName.toLowerCase().equals("phone".toLowerCase())) {
            category=Category.PHONE;
        }else if (categoryName.toLowerCase().equals("laptop".toLowerCase())){
            category=Category.LAPTOP;
        }else if (categoryName.toLowerCase().equals("watch".toLowerCase())){
            category=Category.WATCH;
        }else if (categoryName.toLowerCase().equals("airPods".toLowerCase())){
            category=Category.AIRPODS;}
            else if (categoryName.toLowerCase().equals("iphone".toLowerCase())){
                category=Category.PHONE;
        }else if (categoryName.toLowerCase().equals("Mackbook".toLowerCase())){
            category=Category.LAPTOP;
        }
        else if (categoryName.toLowerCase().equals("Apple Watch".toLowerCase())){
            category=Category.WATCH;
        }
        else if (categoryName.toLowerCase().equals("HEADPHONE".toLowerCase())){
            category=Category.AIRPODS;
        } else category=null;





     return category;

    }


}
