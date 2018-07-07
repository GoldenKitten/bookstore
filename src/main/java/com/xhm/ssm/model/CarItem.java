package com.xhm.ssm.model;

import java.math.BigDecimal;

/**
 * @program: bookstore
 * @description: 购物车条目类
 * @author: 夏红明
 * @date: 2018-05-25 10:21
 * @version: 1.0
 */
public class CarItem {
    private Book book;
    private int count;
    public BigDecimal getSubtotal(){
        BigDecimal b1=new BigDecimal(count+"");
        return book.getPrice().multiply(b1);//BigDecimal可以免去二进制运算误差
    }
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
