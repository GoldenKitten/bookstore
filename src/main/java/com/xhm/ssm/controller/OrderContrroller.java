package com.xhm.ssm.controller;

import com.xhm.ssm.exception.CustomException;
import com.xhm.ssm.model.*;
import com.xhm.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: bookstore
 * @description: 订单控制类
 * @author: 夏红明
 * @date: 2018-05-25 20:18
 * @version: 1.0
 */
@Controller
@RequestMapping("order")
public class OrderContrroller {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomException customException;
    @RequestMapping(value = "addOrder.action",method = RequestMethod.GET)
    public String addOrder(Model model,HttpSession httpSession) throws Exception{
        Car car=(Car) httpSession.getAttribute("car");
        Orders orders=new Orders();
        orders.setOid(UUID.randomUUID().toString().replace("-",""));
        orders.setOrdertime(new Date());
        orders.setState((short) 0);//设置订单状态为0,表示未付款
        User user=(User)httpSession.getAttribute("user");
        orders.setUid(user.getUid());
        orders.setTotal(BigDecimal.valueOf(car.getTotal()));
        /**
         * 创建订单条目
         */
        List<OrderItemExpand> orderItemExpandsList=new ArrayList<OrderItemExpand>();
        for(CarItem carItem:car.getCarItems()){
            OrderItem orderItem=new OrderItem();
            orderItem.setIid(UUID.randomUUID().toString().replace("-",""));
            orderItem.setCount(carItem.getCount());
            orderItem.setBid(carItem.getBook().getBid());
            orderItem.setSubtotal(carItem.getSubtotal());
            orderItem.setOid(orders.getOid());
            OrderItemExpand orderItemExpand=new OrderItemExpand();
            orderItemExpand.setOrderItem(orderItem);
            orderItemExpand.setBook(carItem.getBook());
            orderItemExpandsList.add(orderItemExpand);
        }
        OrdersExpand ordersExpand=new OrdersExpand();
        ordersExpand.setOrders(orders);
        ordersExpand.setOrderItemExpandsList(orderItemExpandsList);
        car.clearAll();
        orderService.addOrders(ordersExpand);
        model.addAttribute("ordersExpand",ordersExpand);
        return "jsps/order/desc";
    }
    @RequestMapping(value = "myOrder.action",method = RequestMethod.GET)
    public String myOrder(Model model,HttpSession httpSession) throws Exception{
        User user= (User) httpSession.getAttribute("user");
        List<OrdersExpand> ordersExpandList=orderService.myOrder(user.getUid());
        model.addAttribute("ordersExpandList",ordersExpandList);
        return "jsps/order/list";
    }
    @RequestMapping(value = "loadOrder.action",method = RequestMethod.GET)
    public String loadOrder(Model model,String oid) throws Exception{
        model.addAttribute("ordersExpand",orderService.loadOrder(oid));
        return "jsps/order/desc";
    }
    @RequestMapping(value = "confirm.action",method = RequestMethod.GET)
    public void confirm(String oid) throws Exception{
        orderService.confirm(oid);
        customException.setMessage("交易成功");
        throw customException;
    }
    @GetMapping("deleteOrder.action")
    public String deleteOrder(Model model,HttpSession httpSession,String oid) throws Exception{
        orderService.delete(oid);
        User user= (User) httpSession.getAttribute("user");
        List<OrdersExpand> ordersExpandList=orderService.myOrder(user.getUid());
        model.addAttribute("ordersExpandList",ordersExpandList);
        return "jsps/order/list";
    }
}
