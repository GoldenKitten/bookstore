package com.xhm.ssm.model;

import com.xhm.ssm.Validator.Group.UserGroup;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Size;
public class User {
    private String uid;
    @NotBlank(message="{user.username.empty.msg}",groups = {UserGroup.LoginValidator.class})
    //@Size(min = 2,max = 10,message = "用户名必须在2到10之间",groups = {UserGroup.LoginValidator.class})
    private String username;
    @NotBlank(message = "密码不能为空",groups = {UserGroup.LoginValidator.class})
    //@Size(min = 6,max = 16,message = "密码必须在6到16之间",groups = {UserGroup.LoginValidator.class})
    private String password;
    @NotBlank(message = "邮箱不能为空",groups = {UserGroup.RegistValidator.class})
    @Email(message = "邮箱不正确",groups = {UserGroup.RegistValidator.class})
    private String email;

    private String code;

    private Boolean state;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}