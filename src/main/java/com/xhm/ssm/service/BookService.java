package com.xhm.ssm.service;

import com.xhm.ssm.model.Book;
import com.xhm.ssm.model.Category;

import java.util.List;

/**
 * @program: bookstore
 * @description: 图书接口类
 * @author: GoldenKitten
 * @date: 2018-05-23 16:49
 * @version: 1.0
 */
public interface BookService {
    public List<Book> findAllBook() throws Exception;
    public List<Book> findBookByCategory(String cid) throws Exception;
    public Book findBook(String bid) throws Exception;
    public void deleteBook(Book book) throws Exception;
    public void editBook(Book book) throws Exception;
    public void insertBook(Book book) throws Exception;
}
