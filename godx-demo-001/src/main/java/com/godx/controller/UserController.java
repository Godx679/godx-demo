package com.godx.controller;

import com.godx.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;

@Controller
@RequestMapping("/user")
@Api(tags = "user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello(){
        return "test";
    }

    @RequestMapping("/add")
    public String add(){
        return "addUser";
    }
    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }
    @RequestMapping("/update")
    public String upate(){
        return "updateUser";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model){
        /*
        * 使用shiro编写用户登录*/
        //1.获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        //2，封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //3,执行登录
        try {
            subject.login(token);
            return "test";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
    /*@ApiOperation(value = "好人",notes = "hr")
    @RequestMapping("/good")
    public String good(){
        return "You are a good man!";
    }*/
    @RequestMapping("/noAuth")
    public String NoAuth(){
        return "noAuth";
    }
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
