package com.xhm.ssm.service.impl;

import com.xhm.ssm.exception.CustomException;
import com.xhm.ssm.exception.LoginValidatorException;
import com.xhm.ssm.exception.RegistValidatorException;
import com.xhm.ssm.mapper.UserMapper;
import com.xhm.ssm.model.User;
import com.xhm.ssm.model.UserExample;
import com.xhm.ssm.service.UserService;
import com.xhm.ssm.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: bookstore
 * @description: 用户业务接口实现
 * @author: 夏红明
 * @date: 2018-05-21 19:40
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private RegistValidatorException registValidatorException;
    @Autowired
    private LoginValidatorException loginValidatorException;
    @Autowired
    private CustomException customException;
    @Override
    @Transactional
    public void regist(User user) throws Exception {
        /**
        * @Description: 注册用户的实现方法
        * @Param: [user] 用户
        * @return: void
        * @Author: 夏红明
        * @Date: 2018/5/23 16:21
        */
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        //添加条件查询
        criteria.andUsernameEqualTo(user.getUsername());
        //通过用户名查询用户
        List<User> users=userMapper.selectByExample(userExample);
        if(users.size()!=0){
            registValidatorException.setMessage("用户名已存在");
            throw registValidatorException;
        }
        //往数据库里添加用户
        userMapper.insert(user);
        String content="<a href='http://localhost:8080/bookstore/user/active.action?code="+user.getCode()+"'"+">点击这里完成激活</a>";
        //发送邮件
        mailUtil.sendEmail(user.getEmail(),content);
    }

    @Override
    @Transactional
    public void active(String code) throws Exception{
        UserExample userExample=new UserExample();
        User userActive=userMapper.selectByPrimaryKey(code);
        if(userActive==null){
            customException.setMessage("激活失败");
            throw customException;
        }
        if(userActive.getState()){
            customException.setMessage("你已经激活成功了");
            throw customException;
        }
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andUidEqualTo(code);
        User user=new User();
        user.setState(true);
        userMapper.updateByExampleSelective(user,userExample);
    }

    @Override
    public User login(User user) throws Exception {
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()!=0){
            criteria.andPasswordEqualTo(user.getPassword());
            users = userMapper.selectByExample(userExample);
            if(users.size()!=0){
                if(users.get(0).getState()){
                    return users.get(0);
                }
                else {
                    loginValidatorException.setMessage("你没有激活该账号,请前往你的邮箱激活");
                    throw loginValidatorException;
                }
            }
            else {
                loginValidatorException.setMessage("密码不正确");
                throw loginValidatorException;
            }
        }
        else {
            loginValidatorException.setMessage("用户名不正确");
            throw loginValidatorException;
        }
    }
}
