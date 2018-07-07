package com.xhm.ssm.exception;

/**
 * @program: workspace
 * @description: 这是一个自定义异常类
 * @author: 夏红明
 * @date: 2018-05-20 12:20
 * @version: 1.0
 */
public class CustomException extends Exception{
    private String message;
    private String view;
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
