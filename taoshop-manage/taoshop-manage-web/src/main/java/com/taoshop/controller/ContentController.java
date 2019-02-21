package com.taoshop.controller;

import com.taoshop.common.pojo.EasyUIResult;
import com.taoshop.common.pojo.TaoResult;
import com.taoshop.pojo.TbContent;
import com.taoshop.service.intf.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    /***
     * 根据ID查询内容并返回EasyUIResult分页对象
     */
    @RequestMapping("/query/list")
    @ResponseBody
    public EasyUIResult getItemList(@RequestParam(defaultValue="1")Integer page,
                                    @RequestParam(defaultValue="20")Integer rows, long categoryId){
        EasyUIResult result = contentService.getItemList(page, rows, categoryId);
        return result;
    }

    /***
     * 新增内容
     * @param content
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public TaoResult insertContent(TbContent content) {
        TaoResult result = contentService.insertContent(content);
        return result;
    }

}
