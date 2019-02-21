package com.taoshop.controller;

import com.taoshop.common.pojo.EasyUIResult;
import com.taoshop.common.pojo.TaoResult;
import com.taoshop.pojo.TbItem;
import com.taoshop.service.intf.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/***
 * 商品信息处理
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    /***
     * 根据商品ID查询商品并返回JSON数据
     * @param itemId
     * @return
     */
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long itemId){
        TbItem item= itemService.getItemById(itemId);
        return item;
    }

    /***
     * 查询所有商品并返回EasyUIResult分页对象
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/item/list")
    //设置相应的内容为json数据
    @ResponseBody
    public EasyUIResult getItemlist(@RequestParam(defaultValue="1")Integer page, @RequestParam(defaultValue="30")Integer rows)
    {
        //查询商品列表
        EasyUIResult result = itemService.getItemList(page, rows);

        return result;
    }

    /***
     * 根据表单添加商品并返回JSON数据
     * @param item
     * @return
     */
    @RequestMapping(value = "/item/save",method = RequestMethod.POST)
    @ResponseBody
    public TaoResult creatItem(TbItem item,String desc,String itemParams) throws Exception {
        TaoResult result= itemService.creatItem(item,desc,itemParams);
        return result;
    }

}
