package com.xhm.ssm.exception;

/**
 * @program: bookstore
 * @description: 注册校验异常
 * @author: 夏红明
 * @date: 2018-05-23 09:22
 * @version: 1.0
 */
public class RegistValidatorException extends Exception{
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
