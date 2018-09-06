package com.xhm.ssm.controller.interceptor;

import com.xhm.ssm.exception.CustomException;
import com.xhm.ssm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: workspace
 * @description: 这是一个拦截器
 * @author: GoldenKitten
 * @date: 2018-05-20 14:59
 * @version: 1.0
 */

public class HandlerInterceptor1 implements HandlerInterceptor {
    @Autowired
    private CustomException customException;
    //进入handler方法之前
    //用于身份认证、身份授权
    //如果没登录，就拦截向下执行
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o) throws Exception {
        //返回false表示拦截
        User user= (User) httpServletRequest.getSession().getAttribute("user");
        String uri=httpServletRequest.getRequestURI();
        if(uri.indexOf("/login.jsp")>=0){
            return true;
        }
        if(uri.indexOf("/bank_img")>=0){
            return true;
        }
        if(uri.indexOf("/book_img")>=0){
            return true;
        }
        if(uri.indexOf("/images")>=0){
            return true;
        }
        if(uri.indexOf("/menu")>=0){
            return true;
        }
        if(uri.indexOf("/jsps")>=0){
            return true;
        }
        if(uri.indexOf("/regist.jsp")>=0){
            return true;
        }
        if(uri.indexOf("/login.action")>=0){
            return true;
        }
        if(uri.indexOf("/regist.action")>=0){
            return true;
        }
         if(uri.indexOf("/jumpRegist.action")>=0){
            return true;
        }
        if(uri.indexOf("/jumpLogin.action")>=0){
            return true;
        }
         if(uri.indexOf("/active.action")>=0){
            return true;
        }
         if(uri.indexOf("/jsps/main.jsp")>=0){
            return true;
        }
         if(uri.indexOf("/admin")>=0){
            if(user!=null&&user.getUsername().equals("xhm")&&user.getPassword().equals("12345678")){
                return true;
            }
            else {
                //httpServletResponse.sendRedirect("/error.jsp");
                httpServletRequest.getRequestDispatcher("/error").forward(httpServletRequest, httpServletResponse);
                return false;
            }
        }
         if(user!=null){
            return true;
        }
        httpServletRequest.setAttribute("msg", "您还没有登录，请先登录！");
        httpServletRequest.getRequestDispatcher("/user/jumpLogin.action").forward(httpServletRequest, httpServletResponse);
        return false;
    }
    //进入handler方法之后，在返回modelAndView之前执行
    //应用场景从modelAndView出发，将公用的模型数据传递进去，也可以在这里统一指定视图
    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }
    //在执行handler方法完成后执行
    //统一异常处理,统一日志处理
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}
