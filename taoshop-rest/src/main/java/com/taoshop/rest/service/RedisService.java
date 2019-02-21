package com.taoshop.rest.service;

import com.taoshop.common.pojo.TaoResult;

public interface RedisService {
    TaoResult syncContent(long contentCid);
}
