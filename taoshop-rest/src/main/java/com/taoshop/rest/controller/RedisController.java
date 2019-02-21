package com.taoshop.rest.controller;

import com.taoshop.common.pojo.TaoResult;
import com.taoshop.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * redis缓存同步处理
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/content/{contentCid}")
    public TaoResult contentCacheSync(@PathVariable Long contentCid) {
        TaoResult result = redisService.syncContent(contentCid);
        return result;
    }
}
