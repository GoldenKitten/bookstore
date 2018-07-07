package com.xhm.ssm.model;

import java.util.List;

/**
 * @program: bookstore
 * @description: 订单扩展类
 * @author: 夏红明
 * @date: 2018-05-25 21:02
 * @version: 1.0
 */
public class OrdersExpand  {
    private Orders orders;
    private List<OrderItemExpand> orderItemExpandsList;//当前订单下所有条目

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public List<OrderItemExpand> getOrderItemExpandsList() {
        return orderItemExpandsList;
    }

    public void setOrderItemExpandsList(List<OrderItemExpand> orderItemExpandsList) {
        this.orderItemExpandsList = orderItemExpandsList;
    }
}
