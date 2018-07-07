package com.xhm.ssm.service;

import com.xhm.ssm.model.Orders;
import com.xhm.ssm.model.OrdersExpand;
import com.xhm.ssm.model.User;

import java.util.List;

/**
 * @program: bookstore
 * @description: 订单接口
 * @author: 夏红明
 * @date: 2018-05-25 20:21
 * @version: 1.0
 */
public interface OrderService {
    public void addOrders(OrdersExpand ordersExpand) throws Exception;
    public List<OrdersExpand> myOrder(String uid) throws Exception;
    public OrdersExpand loadOrder(String oid) throws Exception;
    public void confirm(String oid) throws Exception;
    public void delete(String oid) throws Exception;
    public List<List<OrdersExpand>> findAllOrders() throws Exception;
}
