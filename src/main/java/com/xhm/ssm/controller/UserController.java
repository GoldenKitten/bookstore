package com.xhm.ssm.controller;

import com.xhm.ssm.Validator.Group.UserGroup;
import com.xhm.ssm.model.User;
import com.xhm.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * @program: bookstore
 * @description: 用户控制器
 * @author: 夏红明
 * @date: 2018-05-21 19:42
 * @version: 1.0
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/regist.action",method = RequestMethod.POST)
    public String regist(Model model,
                         @Validated({UserGroup.LoginValidator.class,UserGroup.RegistValidator.class}) User user,
                         BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors=bindingResult.getAllErrors();
            model.addAttribute("msg",allErrors.get(0).getDefaultMessage());
            model.addAttribute("user",user);
            //转发和重定向对视图解析器不起作用
            return "jsps/user/regist";
        }
        String uuid=UUID.randomUUID().toString().replace("-","");
        user.setUid(uuid);
        user.setCode(uuid);
        user.setState(false);
        userService.regist(user);
        model.addAttribute("msg","恭喜注册成功，请到邮箱激活");
        return "jsps/msg";
    }
    @RequestMapping(value = "/active.action",method = RequestMethod.GET)
    public String active(Model model,String code) throws Exception{
        userService.active(code);
        model.addAttribute("msg","激活成功");
        return "jsps/msg";
    }
    @RequestMapping(value = "/login.action",method = RequestMethod.POST)
    public String login(Model model,
                        HttpSession httpSession,
                        @Validated({UserGroup.LoginValidator.class}) User user,
                        BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors=bindingResult.getAllErrors();
            model.addAttribute("msg",allErrors.get(0).getDefaultMessage());
            model.addAttribute("user",user);
            //转发和重定向对视图解析器不起作用
            //默认转发
            return "jsps/user/login";
        }
        User user1=userService.login(user);
        httpSession.setAttribute("user",user1);
        return "redirect:/jsps/main.jsp";
    }
    @RequestMapping(value = "/quit.action",method = RequestMethod.GET)
    public String quit(HttpSession httpSession) throws Exception{
        httpSession.invalidate();
        return "redirect:/jsps/user/login.jsp";
    }
    @GetMapping("jumpRegist.action")
    public String jumpRegist(){
        return "redirect:/jsps/user/regist.jsp";
    }
    @GetMapping("jumpLogin.action")
    public String jumpLogin(){
        return "redirect:/jsps/user/login.jsp";
    }
}
