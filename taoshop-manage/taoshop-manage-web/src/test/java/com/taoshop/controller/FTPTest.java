package com.taoshop.controller;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

public class FTPTest {

    @Test
    public void testFtpClient() throws Exception{
        //创建一个FtpClient对象
        FTPClient ftpClient =new FTPClient();
        //创建ftp链接，默认端口是21
        ftpClient.connect("192.168.41.130",21);
        //登陆ftp服务器
        ftpClient.login("ftpuser","ftpuser");
        //将上传的文件放到指定路径
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        //设置二进制上传形式
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //将其设置为被动模式
        ftpClient.enterLocalPassiveMode();
        //上传文件，需要两个参数 1）用户自定ftp服务器文件名 2）本地文件流InputStream
        FileInputStream in =new FileInputStream(new File("F:\\Master.jpg"));
        boolean result = ftpClient.storeFile("hello.jpg",in);
        //关闭连接
        ftpClient.logout();
        //浏览器访问： 192.168.41.130/images/hello1.jpg 测试能不能正确显示图片
    }
}
