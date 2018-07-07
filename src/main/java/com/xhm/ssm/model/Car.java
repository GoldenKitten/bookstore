package com.xhm.ssm.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: bookstore
 * @description: 购物车
 * @author: 夏红明
 * @date: 2018-05-25 10:22
 * @version: 1.0
 */
public class Car {
    public Map<String,CarItem> map=new LinkedHashMap<String, CarItem>();
    public double getTotal(){
        BigDecimal total=new BigDecimal("0");
        for(CarItem carItem:map.values()){
            total=total.add(carItem.getSubtotal());
        }
        return total.doubleValue();
    }
    public void add(CarItem carItem){
        if(map.containsKey(carItem.getBook().getBid())){//判断购物车是否存在该条目
            CarItem _carItem=map.get(carItem.getBook().getBid());//获取已存在条目
            _carItem.setCount(_carItem.getCount()+carItem.getCount());//更改其数量
            map.put(carItem.getBook().getBid(),_carItem);
        }
        else {
            map.put(carItem.getBook().getBid(),carItem);
        }
    }
    public void clearAll(){
        /** 
        * @Description: 清空购物车所有东西 
        * @Param: [] 
        * @return: void 
        * @Author: 夏红明 
        * @Date: 2018/5/25 10:31
        */ 
        map.clear();
    }
    public void delete(String bid){
        map.remove(bid);
    }
    public Collection<CarItem> getCarItems(){
        return map.values();
    }
}
