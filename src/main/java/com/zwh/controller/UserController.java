package com.zwh.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping("loginUser")
    public String login(String username,String password){
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        //获取token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //验证
        try {
            subject.login(token);
            System.out.println("登陆成功");
            return "index";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登陆失败");
            return "login";
        }
    }
}
