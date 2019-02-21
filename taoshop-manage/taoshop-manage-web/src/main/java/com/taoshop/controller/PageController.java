package com.taoshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * 商品分页处理
 */
@Controller
public class PageController {

    /***
     * 打开首页
     * @return
     */
    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }
    /***
     * 打开其他页面
     * @return
     */
    @RequestMapping("/{page}")
    public String showIndex(@PathVariable String page){
        return page;
    }
}
