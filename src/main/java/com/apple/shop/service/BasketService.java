package com.apple.shop.service;

import com.apple.shop.entity.Basket;
import com.apple.shop.entity.CustomUser;
import com.apple.shop.entity.Goods;
import com.apple.shop.entity.GoodsInBasket;
import com.apple.shop.repositories.BasketRepository;
import com.apple.shop.repositories.GoodsInBasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BasketService {

@Autowired
   private BasketRepository basketRepository;

@Autowired
private GoodsInBasketRepository goodsInBasketRepository;
@Autowired
    GoodsInBasketService goodsInBasketService;


@Autowired
UserService userService;


@Transactional
public Long addBasket(){

    return basketRepository.save(new Basket()).getIdBasket();

}

    @Transactional
    public void updateBasket(GoodsInBasket goodsInBasket){

        // basketRepository.save(goodsInBasket.getBasket());
         goodsInBasketRepository.save(goodsInBasket);

    }



@Transactional
  public void deleteGoodsFromBasket(String model,String name,String memory,CustomUser customUser){



    Basket basket = customUser.getBasket();
    List<GoodsInBasket> goodsInBasket=basket.getGoods();

    for (GoodsInBasket basketGoods: goodsInBasket
    ) {
        if (basketGoods.getGoods().getModel().equals(model) && basketGoods.getGoods().getName().equals(name)  && basketGoods.getMemory().toString().equals(memory)){
            goodsInBasketService.deleteGoodsFromBasket(basketGoods);
        }

    }


}


    @Transactional
public String countOfGoodsInBasket(String emailCurrentUser){


    if (emailCurrentUser==null){
        return "";
    }

    CustomUser customUser = userService.findByEmail(emailCurrentUser);

    if(customUser==null){
        return "";
    }

    if (customUser.getBasket().getGoods()==null){
        return "0";
    }

    int count = getCountOFAllGoods(customUser.getBasket());



return String.valueOf(count);


}



  @Transactional
    public Basket getBasketById(Long idBasket) {
    return basketRepository.getOne(idBasket);
    }


    @Transactional
    public int getCountOFAllGoods(Basket basket) {


    return   basket.getGoods().size();
    }










    private String  getEmailCurrentUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

        return email;
    }

}
