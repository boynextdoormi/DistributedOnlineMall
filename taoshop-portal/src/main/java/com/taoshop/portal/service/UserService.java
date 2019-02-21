package com.taoshop.portal.service;

import com.taoshop.pojo.TbUser;

public interface UserService {
    TbUser getUserByToken(String token);
}
