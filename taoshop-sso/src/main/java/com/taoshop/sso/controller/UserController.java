package com.taoshop.sso.controller;

import com.taoshop.common.pojo.TaoResult;
import com.taoshop.common.utils.ExceptionUtil;
import com.taoshop.pojo.TbUser;
import com.taoshop.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * 用户登陆处理
 */
@Controller
@RequestMapping("/user") //URL: http://localhost:8084/user/check/{param}/{type}
public class UserController {

    @Autowired
    private UserService userService;

    //检查有效性
    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {

        TaoResult result = null;

        //参数有效性校验
        if (StringUtils.isBlank(param)) {
            result = TaoResult.build(400, "校验内容不能为空");
        }
        if (type == null) {
            result = TaoResult.build(400, "校验内容类型不能为空");
        }
        if (type != 1 && type != 2 && type != 3 ) {
            result = TaoResult.build(400, "校验内容类型错误");
        }
        //校验出错
        if (null != result) {
            if (null != callback) {
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            } else {
                return result;
            }
        }
        //调用服务
        try {
            result = userService.checkData(param, type);

        } catch (Exception e) {
            result = TaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

        if (null != callback) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        } else {
            return result;
        }
    }

    //创建用户
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public TaoResult createUser(TbUser user) {

        try {
            TaoResult result = userService.createUser(user);
            return result;
        } catch (Exception e) {
            return TaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    //用户登录
    @RequestMapping(value="/login", method=RequestMethod.POST)
    @ResponseBody
    public TaoResult userLogin(String username, String password,
                               HttpServletRequest request, HttpServletResponse response) {
        try {

            TaoResult result = userService.userLogin(username, password,request,response);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    //http://sso.taotao.com/user/logout/{token}
    //安全登出
    @RequestMapping("/logout/{token}")
    @ResponseBody
    public Object logout(@PathVariable String token, String callback) {
        TaoResult result = TaoResult.ok();
        try {
            result = userService.getUserByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            result = TaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

        //判断是否为jsonp调用
        if (StringUtils.isBlank(callback)) {
            return result;
        } else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }

    }

    //根据token返回User对象
    @RequestMapping("/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token, String callback) {
        TaoResult result = null;
        try {
            result = userService.getUserByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            result = TaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

        //判断是否为jsonp调用
        if (StringUtils.isBlank(callback)) {
            return result;
        } else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }

    }
}
