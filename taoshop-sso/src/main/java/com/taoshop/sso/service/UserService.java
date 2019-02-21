package com.taoshop.sso.service;

import com.taoshop.common.pojo.TaoResult;
import com.taoshop.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    TaoResult checkData(String content,Integer type);
    TaoResult createUser(TbUser user);
    TaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
    TaoResult getUserByToken(String token);
}
