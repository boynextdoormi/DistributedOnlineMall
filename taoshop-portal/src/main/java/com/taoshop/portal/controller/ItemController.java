package com.taoshop.portal.controller;

import com.taoshop.pojo.TbItem;
import com.taoshop.portal.pojo.ItemInfo;
import com.taoshop.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 商品信息页面处理
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    /***
     * 商品详情页面展示
     * @param itemId
     * @param model
     * @return
     */
    @RequestMapping("/item/{itemId}")
    public String showItem(@PathVariable Long itemId, Model model) {
        ItemInfo item = itemService.getItemById(itemId);
        model.addAttribute("item", item);

        return "item";
    }

    /***
     * 商品描述展示
     * @param itemId
     * @return
     */
    @RequestMapping(value="/item/desc/{itemId}", produces= MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemDesc(@PathVariable Long itemId) {
        String string = itemService.getItemDescById(itemId);
        return string;
    }

    /***
     * 商品规格参数展示
     * @param itemId
     * @return
     */
    @RequestMapping(value="/item/param/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemParam(@PathVariable Long itemId) {
        String string = itemService.getItemParam(itemId);
        return string;
    }
}
