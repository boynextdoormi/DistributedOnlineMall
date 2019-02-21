package com.taoshop.rest.controller;

import com.taoshop.common.pojo.TaoResult;
import com.taoshop.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//  Url：/rest/item/info/{itemId}154658971579784
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/info/{itemId}")
    @ResponseBody
    public TaoResult getItemBaseInfo(@PathVariable Long itemId) {
        TaoResult result = itemService.getItemBaseInfo(itemId);
        return result;
    }

    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public TaoResult getItemDesc(@PathVariable Long itemId) {
        TaoResult result = itemService.getItemDesc(itemId);
        return result;
    }

    @RequestMapping("/param/{itemId}")
    @ResponseBody
    public TaoResult getItemParam(@PathVariable Long itemId) {
        TaoResult result = itemService.getItemParam(itemId);
        return result;
    }

}