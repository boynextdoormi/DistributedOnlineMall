package com.taoshop.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * 页面跳转controller
 */
@Controller
@RequestMapping("/page")
public class PageController {

    //注册页面
    @RequestMapping("/register")
    public String showRegister() {
        return "register";
    }
    //登陆页面
    @RequestMapping("/login")
    public String showLogin(String redirect, Model model) {
        model.addAttribute("redirect",redirect);
        return "login";
    }
}