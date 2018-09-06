package com.xhm.ssm.service;

import com.xhm.ssm.model.Category;

import java.util.List;

/**
 * @program: bookstore
 * @description: 图书分类接口
 * @author: GoldenKitten
 * @date: 2018-05-23 16:24
 * @version: 1.0
 */
public interface CategoryService {
    public List<Category> findAllCategory() throws Exception;
    public void addCategory(String cname) throws Exception;
    public void deleteCategory(String cid) throws Exception;
    public Category findCategory(String cid) throws Exception;
    public void editCategory(Category category) throws Exception;
}
