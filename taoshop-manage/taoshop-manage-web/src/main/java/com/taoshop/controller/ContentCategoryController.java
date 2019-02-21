package com.taoshop.controller;


import com.taoshop.common.pojo.EUTreeNode;
import com.taoshop.common.pojo.TaoResult;
import com.taoshop.service.intf.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * 内容分类处理
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCatList(@RequestParam(value = "id",
            defaultValue = "0") long parentId){
        List<EUTreeNode> list = contentCategoryService.getCategoryList(parentId);
        return list;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaoResult createContentCategory(Long parentId, String name) {
        TaoResult result = contentCategoryService.insertContentCategory(parentId, name);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaoResult createContentCategory(Long parentId, long id) {
        TaoResult result = contentCategoryService.deleteContentCategory(parentId, id);
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public TaoResult createContentCategory(long id,String name) {
        TaoResult result = contentCategoryService.updateContentCategory(id,name);
        return result;
    }

}
