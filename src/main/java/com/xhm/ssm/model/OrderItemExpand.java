package com.xhm.ssm.model;

/**
 * @program: bookstore
 * @description: 订单条目扩展类
 * @author: 夏红明
 * @date: 2018-05-25 21:26
 * @version: 1.0
 */
public class OrderItemExpand {
    private OrderItem orderItem;
    private Book book;

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
