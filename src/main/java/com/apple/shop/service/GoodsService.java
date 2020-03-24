package com.apple.shop.service;


import com.apple.shop.entity.Category;
import com.apple.shop.entity.Goods;

import com.apple.shop.repositories.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class GoodsService  {


    @Autowired
    GoodsRepository goodsRepository;


    @Autowired
    CategoryService categoryService;







    @Transactional
    public boolean saveGoods(Goods goods){


        Goods existingGoods = goodsRepository.findTopByModelAndCategoryAndNameIgnoreCase(goods.getModel(),goods.getCategory(),goods.getName());

        if (existingGoods==null){
            goodsRepository.save(goods);
        }else {
            if (existingGoods.getMemoryPrice().equals(goods.getMemoryPrice())) {

                existingGoods.setCount(existingGoods.getCount() + goods.getCount());
                goodsRepository.save(existingGoods);


            }else{

                goodsRepository.save(goods);

            }




        }

        return true;
    }


    @Transactional
    public Goods getGoodsById(Long id){

        return goodsRepository.getOne(id);
    }




    @Transactional
    public void upgradeGoods(Goods goods){

goodsRepository.save(goods);

    }



    @Transactional
    public Goods getDefaultGoodsByModel(String name,String category) {
        String m = name;

        List<Goods> goods = new ArrayList<>();
        if (categoryService.getCategoryByString(category)==null){

            goods=goodsRepository.findByModel(name);

        }else {
            goods = goodsRepository.findByModelAndCategory(name, categoryService.getCategoryByString(category));
        }
        int size = goods.size();

        if (goods.size()>0){
            if (goods.get(0).getCategory().equals(Category.AIRPODS)){
                return   goods.get(0);
            }
        }

        List<Integer> memories = new ArrayList<>();
        Integer memory = goods.get(0).getMemoryPrice().keySet().stream().findFirst().get();

        for (Goods g: goods
        ) {
            memories.add(g.getMemoryPrice().keySet().stream().findFirst().get());
        }
        Collections.sort(memories);

        for (Goods g: goods
        ) {
            if (g.getMemoryPrice().keySet().stream().findFirst().get()==memories.get(0)){
                return g;
            }
        }

        return goods.get(0);
    }





    public boolean modelHasMemory(String category , String model){



        String[] ch = model.split("");


        List<Goods> goods=new ArrayList<>();
        goods.addAll( goodsRepository.findDistinctByCategoryAndModelStartingWith(categoryService.getCategoryByString(category),ch[0]));

        if (goods.size()>0) {
            if (goods.get(0).getCategory().equals(Category.AIRPODS)) {
                return false;
            } else return true;
        }else return false;
    }




    public Set<String> findByModelContains(String category , String model){

        List<String> res = new ArrayList<>();


        String[] ch = model.split("");


        List<Goods> goods=new ArrayList<>();
        goods.addAll( goodsRepository.findDistinctByCategoryAndModelStartingWith(categoryService.getCategoryByString(category),ch[0]));


        for (Goods g: goods
        ) {

            res.add(g.getModel());
        }

        Set<String> result = new HashSet<>(res);

        return result;
    }





    public Set<Integer> getMemoriesByModel(String model){


        Set<Integer> res = new TreeSet<>();

        List<Goods> models= goodsRepository.findByModel(model);



        for (Goods g: models
        ) {

            res.addAll(g.getMemoryPrice().keySet());
        }

        return res;

    }


    public Goods findByModelAndMemory(String model,Integer memory) {

        List<Goods> goods = goodsRepository.findByModel(model);
        Goods ourGoods = null;
        for (Goods g: goods
        ) {
            if (g.getMemoryPrice().get(memory)!=null){
                ourGoods=g;

            }
        }
        return ourGoods;
    }




    public Goods findByModelAndMemory(String model,Integer memory,Category category) {

        List<Goods> goods = goodsRepository.findByModel(model);
        Goods ourGoods = null;
        for (Goods g: goods
        ) {
            if (g.getMemoryPrice().get(memory)!=null && g.getCategory().equals(category)){
                ourGoods=g;

            }
        }
        return ourGoods;
    }





    public List<Goods> getGoodsByModel(String name) {

        return  goodsRepository.findByModel(name);

    }
}
