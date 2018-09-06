package com.xhm.ssm.service;

import com.xhm.ssm.model.User;
import org.springframework.stereotype.Service;

/**
 * @program: bookstore
 * @description: 用户业务接口
 * @author: GoldenKitten
 * @date: 2018-05-21 19:29
 * @version: 1.0
 */
public interface UserService {
    //用户注册方法
    public void regist(User user) throws Exception;
    //用户激活方法
    public void active(String code) throws Exception;
    //用户登录方法
    public User login(User user) throws Exception;
}
