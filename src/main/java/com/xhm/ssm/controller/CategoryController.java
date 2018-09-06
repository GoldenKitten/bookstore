package com.xhm.ssm.controller;

import com.xhm.ssm.model.Category;
import com.xhm.ssm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @program: bookstore
 * @description: 图书分类控制器
 * @author: GoldenKitten
 * @date: 2018-05-23 16:32
 * @version: 1.0
 */
@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(value = "/findall.action",method = RequestMethod.GET)
    public String findAllCategory(Model model) throws Exception{
        List<Category> categoryList= categoryService.findAllCategory();
        model.addAttribute("categoryList",categoryList);
        return "jsps/left";
    }
}
