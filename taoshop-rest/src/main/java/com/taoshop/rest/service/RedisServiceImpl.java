package com.taoshop.rest.service;

import com.taoshop.common.pojo.TaoResult;
import com.taoshop.common.utils.ExceptionUtil;
import com.taoshop.rest.dao.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public TaoResult syncContent(long contentCid) {
        try {
            jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid + "");
        } catch (Exception e) {
            e.printStackTrace();
            return TaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaoResult.ok();
    }

}