package com.xhm.ssm.exception;

/**
 * @program: bookstore
 * @description: 注册校验异常
 * @author: GoldenKitten
 * @date: 2018-05-23 09:22
 * @version: 1.0
 */
public class LoginValidatorException extends Exception{
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
