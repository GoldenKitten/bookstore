package com.xhm.ssm.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @program: bookstore
 * @description: 邮件工具类
 * @author: 夏红明
 * @date: 2018-05-22 13:20
 * @version: 1.0
 */
@Component
public class MailUtil {
    @Autowired
    private MailSender mailSender;
    @Autowired
    private SimpleMailMessage simpleMailMessage;
    public  void sendEmail(String to,String content) throws Exception{
        /**
        * @Description:  发送邮件
        * @Param: [to, content] to 收件方邮箱,content 邮件内容
        * @return: void
        * @Author: 夏红明
        * @Date: 2018/5/23 16:15
        */
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(content);
        //发送邮件
        mailSender.send(simpleMailMessage);
    }

}
