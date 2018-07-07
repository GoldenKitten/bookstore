package com.xhm.ssm.service.impl;

import com.xhm.ssm.exception.CustomException;
import com.xhm.ssm.mapper.BookMapper;
import com.xhm.ssm.mapper.CategoryMapper;
import com.xhm.ssm.model.Book;
import com.xhm.ssm.model.BookExample;
import com.xhm.ssm.model.Category;
import com.xhm.ssm.model.CategoryExample;
import com.xhm.ssm.service.BookService;
import com.xhm.ssm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @program: bookstore
 * @description: 图书分类接口实现
 * @author: 夏红明
 * @date: 2018-05-23 16:27
 * @version: 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CustomException customException;
    @Override
    public List<Category> findAllCategory() throws Exception{
        /** 
        * @Description: 查询所有图书分类 
        * @Param: [] 
        * @return: java.util.List<com.xhm.ssm.model.Category> 
        * @Author: 夏红明 
        * @Date: 2018/5/23 16:32
        */ 
        CategoryExample categoryExample=new CategoryExample();
        return categoryMapper.selectByExample(categoryExample);
    }

    @Override
    public void addCategory(String cname) throws Exception {
        Category category=new Category();
        category.setCid(UUID.randomUUID().toString().replace("-",""));
        category.setCname(cname);
        categoryMapper.insert(category);
    }

    @Override
    public void deleteCategory(String cid) throws Exception {
        BookExample bookExample=new BookExample();
        BookExample.Criteria criteria = bookExample.createCriteria();
        criteria.andCidEqualTo(cid);
        List<Book> bookList=bookMapper.selectByExample(bookExample);
        if(bookList!=null && !bookList.isEmpty()){
            customException.setMessage("图书下有该分类,不能删除");
            throw customException;
        }
        categoryMapper.deleteByPrimaryKey(cid);
    }

    @Override
    public Category findCategory(String cid) throws Exception {
        return categoryMapper.selectByPrimaryKey(cid);
    }

    @Override
    public void editCategory(Category category) throws Exception {
        categoryMapper.updateByPrimaryKey(category);
        customException.setMessage("修改成功");
        throw  customException;
    }
}
