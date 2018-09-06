package com.xhm.ssm.Validator.Group;

import javax.validation.GroupSequence;

/**
 * @program: bookstore
 * @description: 这是一个分组接口
 * @author: GoldenKitten
 * @date: 2018-05-22 23:58
 * @version: 1.0
 */
@GroupSequence({UserGroup.LoginValidator.class,UserGroup.RegistValidator.class})
public interface UserGroup {
    public interface RegistValidator{};//这是注册时校验分组
    public interface LoginValidator{};//这是登录时校验分组
}
