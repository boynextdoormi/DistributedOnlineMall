package com.taoshop.service.impl;

import com.taoshop.common.utils.FtpUtil;
import com.taoshop.common.utils.IDUtils;
import com.taoshop.service.intf.PictureService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PictureServiceImlp implements PictureService {
    @Value("${FTP_ADDRESS}")
    public String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    public String FTP_PORT;
    @Value("${FTP_USERNAME}")
    public String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    public String FTP_PASSWORD;
    @Value("${FTP_BASE_PATH}")
    public String FTP_BASE_PATH;
    @Value("${IMAGE_BASE_URL}")
    public String IMAGE_BASE_URL;


    /***
     * 上传图片
     * @param uploadFile
     * @return
     */
    @Override
    public Map uploadPicture(MultipartFile uploadFile) {
        Map resultMap =new HashMap();
        try {
            //生成一个新的文件名
            String oldName =uploadFile.getOriginalFilename();
            //使用一个ID生成工具
            String newName = IDUtils.genImageName();
            newName =newName +oldName.substring(oldName.indexOf("."));
            //图片上传
            String imagePath=new DateTime().toString("/yyyy/MM/dd");
            //int port = Integer.parseInt(FTP_PORT);
            boolean result = FtpUtil.uploadFile(FTP_ADDRESS,Integer.parseInt(FTP_PORT),FTP_USERNAME,FTP_PASSWORD,FTP_BASE_PATH,
                    imagePath,newName,uploadFile.getInputStream());
            if (!result){
                resultMap.put("error",1);
                resultMap.put("message","文件上传失败");
                return resultMap;
            }
            resultMap.put("error",0);
            resultMap.put("url",IMAGE_BASE_URL+imagePath+"/"+newName);
            return resultMap;
        } catch (IOException e) {
            resultMap.put("error",1);
            resultMap.put("message","文件上传发生异常");
            return resultMap;
        }
    }
}
