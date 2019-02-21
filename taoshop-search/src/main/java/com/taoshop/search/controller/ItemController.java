package com.taoshop.search.controller;

import com.taoshop.common.pojo.TaoResult;
import com.taoshop.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//将数据库相关数据导入到SOLR索引库中 http://localhost:8083/search/manager/importall
@Controller
@RequestMapping("/manager")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 导入商品数据到索引库
     */
    @RequestMapping("/importall")
    @ResponseBody
    public TaoResult importAllItems() {
        TaoResult result = itemService.importAllItems();
        return result;
    }
}