package com.xhm.ssm.controller;

import com.xhm.ssm.model.Book;
import com.xhm.ssm.model.Car;
import com.xhm.ssm.model.CarItem;
import com.xhm.ssm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @program: bookstore
 * @description: 购物车控制类
 * @author: GoldenKitten
 * @date: 2018-05-25 11:09
 * @version: 1.0
 */
@Controller
@RequestMapping("car")
public class CarController {
    @Autowired
    private BookService bookService;
    @RequestMapping(value = "addCar.action",method = RequestMethod.POST)
    public String add(HttpSession session,Model model, String bid, String count) throws Exception{
        Car car= (Car) session.getAttribute("car");
        if(car==null){
            car=new Car();
        }
        Book book=bookService.findBook(bid);
        int count1=Integer.parseInt(count);
        CarItem carItem=new CarItem();
        carItem.setBook(book);
        carItem.setCount(count1);
        car.add(carItem);
        session.setAttribute("car",car);
        return "jsps/cart/list";
    }
    @RequestMapping(value = "clearAll.action",method = RequestMethod.GET)
    public String clearAll(Model model,HttpSession session) throws Exception{
        Car car= (Car) session.getAttribute("car");
        car.clearAll();
        session.setAttribute("car",car);
        return "jsps/cart/list";
    }
    @RequestMapping(value = "delete.action",method = RequestMethod.GET)
    public String delete(Model model,String bid,HttpSession session) throws Exception{
        Car car= (Car) session.getAttribute("car");
        car.delete(bid);
        session.setAttribute("car",car);
        return "jsps/cart/list";
    }
}
