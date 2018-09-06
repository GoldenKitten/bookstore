package com.xhm.ssm.service.impl;

import com.xhm.ssm.exception.CustomException;
import com.xhm.ssm.mapper.BookMapper;
import com.xhm.ssm.model.Book;
import com.xhm.ssm.model.BookExample;
import com.xhm.ssm.model.Category;
import com.xhm.ssm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: bookstore
 * @description: 图书接口类的实现
 * @author: GoldenKitten
 * @date: 2018-05-23 16:54
 * @version: 1.0
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CustomException customException;
    @Override
    public List<Book> findAllBook() throws Exception {
        BookExample bookExample=new BookExample();
       return bookMapper.selectByExample(bookExample);
    }

    @Override
    public List<Book> findBookByCategory(String cid) throws Exception {
        BookExample bookExample=new BookExample();
        BookExample.Criteria criteria = bookExample.createCriteria();
        criteria.andCidEqualTo(cid);
        return bookMapper.selectByExample(bookExample);
    }

    @Override
    public Book findBook(String bid) throws Exception {
        return bookMapper.selectByPrimaryKey(bid);
    }

    @Override
    public void deleteBook(Book book) throws Exception {
        bookMapper.deleteByPrimaryKey(book.getBid());
        customException.setMessage("删除成功");
        throw customException;
    }

    @Override
    public void editBook(Book book) throws Exception {
        bookMapper.updateByPrimaryKeySelective(book);
        customException.setMessage("修改成功");
        throw customException;
    }

    @Override
    public void insertBook(Book book) throws Exception {
        bookMapper.insert(book);
    }
}
