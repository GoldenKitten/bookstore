package com.xhm.ssm.controller;

import com.xhm.ssm.model.Book;
import com.xhm.ssm.model.Category;
import com.xhm.ssm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @program: bookstore
 * @description: 图书控制类
 * @author: GoldenKitten
 * @date: 2018-05-23 16:55
 * @version: 1.0
 */
@Controller
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookService bookService;
    @RequestMapping(value = "/findAllBook.action",method = RequestMethod.GET)
    public String findAllBook(Model model) throws Exception{
        List<Book> bookList=bookService.findAllBook();
        model.addAttribute("bookList",bookList);
        return "jsps/book/list";
    }
    @RequestMapping(value = "/findBookByCategory.action",method = RequestMethod.GET)
    public String findBookByCategory(Model model, int cid) throws Exception{
        List<Book> bookList=bookService.findBookByCategory(String.valueOf(cid));
        model.addAttribute("bookList",bookList);
        return "jsps/book/list";
    }
    @RequestMapping(value = "/findBook.action",method = RequestMethod.GET)
    public String findBook(Model model,int bid) throws Exception{
        Book book=bookService.findBook(String.valueOf(bid));
        model.addAttribute("book",book);
        return "jsps/book/desc";
    }
}
