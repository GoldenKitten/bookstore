package com.xhm.ssm.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xhm.ssm.model.Book;
import com.xhm.ssm.model.Category;
import com.xhm.ssm.model.OrdersExpand;
import com.xhm.ssm.service.BookService;
import com.xhm.ssm.service.CategoryService;
import com.xhm.ssm.service.OrderService;
import com.xhm.ssm.utils.L;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * @program: bookstore
 * @description: 管理员控制类
 * @author: 夏红明
 * @date: 2018-05-26 11:15
 * @version: 1.0
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "findAllCategory.action",method = RequestMethod.GET)
    public String findAllCategory(Model model) throws Exception{
        List<Category> categoryList=categoryService.findAllCategory();
        model.addAttribute("categoryList",categoryList);
        return "adminjsps/admin/category/list";
    }
    @RequestMapping(value = "addCategory.action",method = RequestMethod.POST)
    public String addCategory(Model model,String cname) throws Exception{
       categoryService.addCategory(cname);
       model.addAttribute("msg","添加分类成功");
        return "adminjsps/admin/category/add";
    }
    @RequestMapping(value = "deleteCategory.action",method = RequestMethod.GET)
    public String deleteCategory(Model model,String cid) throws Exception{
        categoryService.deleteCategory(cid);
        model.addAttribute("msg","删除成功");
        return "adminjsps/admin/category/del";
    }
    @RequestMapping(value = "editCategoryPre.action",method = RequestMethod.GET)
    public String editCategoryPre(Model model,String cid) throws Exception{
        model.addAttribute("category",categoryService.findCategory(cid));
        return "adminjsps/admin/category/mod";
    }
    @RequestMapping(value = "editCategory.action",method = RequestMethod.POST)
    public void editCategory(Model model,Category category) throws Exception{
        categoryService.editCategory(category);
    }
    @RequestMapping(value = "findAllBook.action",method = RequestMethod.GET)
    public String findAllBook(Model model) throws Exception{
        List<Book> bookList=bookService.findAllBook();
        model.addAttribute("bookList",bookList);
        return "adminjsps/admin/book/list";
    }
    @RequestMapping(value = "findBook.action",method = RequestMethod.GET)
    public String findBook(Model model,String bid) throws Exception{
        Book book=bookService.findBook(bid);
        List<Category> categoryList=categoryService.findAllCategory();
        Category bookCategory=categoryService.findCategory(book.getCid());
        Iterator<Category> iterator=categoryList.iterator();
        while (iterator.hasNext()){
            if(iterator.next().getCid().equals(bookCategory.getCid())){
                iterator.remove();
            }
        }
        model.addAttribute("book",book);
        model.addAttribute("bookCategory",bookCategory);
        model.addAttribute("categoryList",categoryList);
        return "adminjsps/admin/book/desc";
    }
    @RequestMapping(value = "editBook.action",method = RequestMethod.POST)
    public void editBook(String price,Book book) throws Exception{
        book.setPrice(new BigDecimal(price));
            bookService.editBook(book);
    }
    @RequestMapping(value = "delBook.action",method = RequestMethod.POST)
    public void delBook(String price,Book book) throws Exception{
        book.setPrice(new BigDecimal(price));
            bookService.deleteBook(book);
    }
    @GetMapping("addBookPre.action")
    public String addBookPre(HttpSession httpSession) throws Exception{
        List<Category> allCategory= (List<Category>) httpSession.getAttribute("allCategory");
        if(allCategory==null) {
           allCategory = categoryService.findAllCategory();
        }
        httpSession.setAttribute("allCategory",allCategory);
        return "/adminjsps/admin/book/add";
    }
    @PostMapping("addBook.action")
    public String addBook(HttpServletRequest httpServletRequest,
                        Model model,
                        String price,
                        Book book,
                        MultipartFile pic) throws Exception{
        if(pic!=null){
            String savePath=httpServletRequest.getServletContext().getRealPath("/book_img");
            String originalFileName=pic.getOriginalFilename();
            String newFileName=UUID.randomUUID().toString().replace("-","")+originalFileName;
            File newFile=new File(savePath,newFileName);
            pic.transferTo(newFile);
            book.setPrice(new BigDecimal(price));
            book.setBid(UUID.randomUUID().toString().replace("-",""));
            book.setImage("book_img/"+newFileName);
            bookService.insertBook(book);
            model.addAttribute("msg","上传成功");
        }
        else {
            model.addAttribute("msg","请上传图片");
        }
        return "adminjsps/admin/book/add";
    }
    @GetMapping("findAllOrders.action")
    public String findAllOrders(Model model) throws Exception{
        List<List<OrdersExpand>> all=orderService.findAllOrders();
        model.addAttribute("title","所有订单");
        model.addAttribute("all",all);
        return "adminjsps/admin/order/list";
    }
    @GetMapping("findOrdersByState.action")
    public String findOrdersByState(String title,String state,Model model) throws Exception{
        Short s=new Short(state);

        List<List<OrdersExpand>> all=orderService.findAllOrders();
        List<List<OrdersExpand>> newAll=new ArrayList<>();
        List<OrdersExpand> ordersExpands=new ArrayList<>();
        for(List<OrdersExpand> ordersExpandList:all){
            for(OrdersExpand ordersExpand:ordersExpandList){
                if(ordersExpand.getOrders().getState().shortValue()==s.shortValue()){
                    ordersExpands.add(ordersExpand);
                }
            }
           newAll.add(ordersExpands);
        }
        model.addAttribute("title",title);
        model.addAttribute("all",newAll);
        System.out.println(s);
        return "adminjsps/admin/order/list";
    }
}
