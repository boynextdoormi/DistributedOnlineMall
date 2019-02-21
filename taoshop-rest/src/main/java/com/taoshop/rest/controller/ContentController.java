package com.taoshop.rest.controller;

import com.taoshop.common.pojo.TaoResult;
import com.taoshop.common.utils.ExceptionUtil;
import com.taoshop.pojo.TbContent;
import com.taoshop.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/list/{contentCategoryId}")
    @ResponseBody
    public TaoResult getContentList(@PathVariable Long contentCategoryId) {
        try {
            List<TbContent> list = contentService.getContentList(contentCategoryId);
            return TaoResult.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return TaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
