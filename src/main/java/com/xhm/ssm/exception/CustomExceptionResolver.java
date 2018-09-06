package com.xhm.ssm.exception;


import com.xhm.ssm.utils.L;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: workspace
 * @description: 全局异常处理器
 * @author: GoldenKitten
 * @date: 2018-05-20 12:26
 * @version: 1.0
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o,
                                         Exception e) {
        ModelAndView modelAndView=new ModelAndView();
        String message;
        if(e instanceof CustomException){
            message= ((CustomException)e).getMessage();
            modelAndView.setViewName(((CustomException)e).getView());
            L.i(message);
        }
        else if(e instanceof LoginValidatorException){
            message=((LoginValidatorException)e).getMessage();
            modelAndView.setViewName(((LoginValidatorException)e).getView());
            L.i(message);
        }
        else if(e instanceof RegistValidatorException){
            message=((RegistValidatorException)e).getMessage();
            modelAndView.setViewName(((RegistValidatorException)e).getView());
            L.i(message);
        }
        else {
            message="未知错误";
            modelAndView.setViewName("jsps/msg");
            L.e(e.getMessage());

        }
        modelAndView.addObject("msg",message);
        return modelAndView;
    }
}
