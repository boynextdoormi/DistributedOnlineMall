package com.taoshop.controller;


import com.taoshop.common.utils.JsonUtils;
import com.taoshop.service.intf.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/***
 * 图片上传处理
 */
@Controller
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile){
        Map result=pictureService.uploadPicture(uploadFile);
        String json = JsonUtils.objectToJson(result);
        return json;
    }

}
