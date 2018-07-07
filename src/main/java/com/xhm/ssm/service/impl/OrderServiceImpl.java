package com.xhm.ssm.service.impl;

import com.xhm.ssm.exception.CustomException;
import com.xhm.ssm.mapper.OrderItemMapper;
import com.xhm.ssm.mapper.OrdersMapper;
import com.xhm.ssm.model.*;
import com.xhm.ssm.service.BookService;
import com.xhm.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: bookstore
 * @description: 订单实现类
 * @author: 夏红明
 * @date: 2018-05-25 20:29
 * @version: 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private BookService bookService;
    @Autowired
    private CustomException customException;
    @Transactional
    @Override
    public void addOrders(OrdersExpand ordersExpand) throws Exception {
        ordersMapper.insert(ordersExpand.getOrders());
        for(OrderItemExpand orderItemExpand:ordersExpand.getOrderItemExpandsList()){
            orderItemMapper.insert(orderItemExpand.getOrderItem());
        }
    }

    @Override
    public List<OrdersExpand> myOrder(String uid) throws Exception {
        List<OrdersExpand> ordersExpandList=new ArrayList<OrdersExpand>();
        OrdersExample ordersExample=new OrdersExample();
        OrdersExample.Criteria criteria = ordersExample.createCriteria();
        criteria.andUidEqualTo(uid);
        List<Orders> ordersList=ordersMapper.selectByExample(ordersExample);
        for(Orders orders:ordersList){
            OrdersExpand ordersExpand=new OrdersExpand();
            ordersExpand.setOrders(orders);
            OrderItemExample orderItemExample=new OrderItemExample();
            OrderItemExample.Criteria criteria1 = orderItemExample.createCriteria();
            criteria1.andOidEqualTo(orders.getOid());
            List<OrderItem> orderItemList=orderItemMapper.selectByExample(orderItemExample);
            List<OrderItemExpand> orderItemExpandList=new ArrayList<OrderItemExpand>();
            for(OrderItem orderItem:orderItemList){
                OrderItemExpand orderItemExpand=new OrderItemExpand();
                orderItemExpand.setOrderItem(orderItem);
                orderItemExpand.setBook(bookService.findBook(orderItem.getBid()));
                orderItemExpandList.add(orderItemExpand);
            }
            ordersExpand.setOrderItemExpandsList(orderItemExpandList);
            ordersExpandList.add(ordersExpand);
        }
        return ordersExpandList;
    }

    @Override
    public OrdersExpand loadOrder(String oid) throws Exception {
            OrdersExpand ordersExpand=new OrdersExpand();
            Orders orders=ordersMapper.selectByPrimaryKey(oid);
            ordersExpand.setOrders(orders);
            OrderItemExample orderItemExample=new OrderItemExample();
            OrderItemExample.Criteria criteria = orderItemExample.createCriteria();
            criteria.andOidEqualTo(orders.getOid());
            List<OrderItem> orderItemList=orderItemMapper.selectByExample(orderItemExample);
            List<OrderItemExpand> orderItemExpandList= new ArrayList<>();
            for(OrderItem orderItem:orderItemList){
                OrderItemExpand orderItemExpand=new OrderItemExpand();
                orderItemExpand.setOrderItem(orderItem);
                orderItemExpand.setBook(bookService.findBook(orderItem.getBid()));
                orderItemExpandList.add(orderItemExpand);
            }
            ordersExpand.setOrderItemExpandsList(orderItemExpandList);
            return ordersExpand;
        }

    @Override
    public void confirm(String oid) throws Exception {
        Orders orders=ordersMapper.selectByPrimaryKey(oid);
        if(orders==null){
            customException.setMessage("没有该订单");
            throw customException;
        }
        else if(orders.getState()!=2){
            customException.setMessage("订单确认付款失败");
            throw customException;
        }
        else {
            Orders orders1=new Orders();
            orders1.setOid(oid);
            orders1.setState((short) 3);
            ordersMapper.updateByPrimaryKeySelective(orders1);

        }
    }
}
