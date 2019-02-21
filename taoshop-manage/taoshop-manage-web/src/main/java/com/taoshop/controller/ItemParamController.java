package com.taoshop.controller;

import com.taoshop.common.pojo.TaoResult;
import com.taoshop.pojo.TbItemParam;
import com.taoshop.service.intf.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 规格参数处理
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;


    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public TaoResult getItemParamByCid(@PathVariable Long itemCatId){
        TaoResult result=itemParamService.getItemParamByCid(itemCatId);
        return result;
    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaoResult getItemParamByCid(@PathVariable Long cid,String paramData){
        TbItemParam itemParam =new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        TaoResult result=itemParamService.insertItemParam(itemParam);
        return result;
    }


}
