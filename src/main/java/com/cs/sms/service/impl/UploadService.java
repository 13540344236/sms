package com.cs.sms.service.impl;

import com.cs.sms.service.IUploadService;
import com.cs.sms.util.OssUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 图片上传实现类
 */
@Slf4j
@Service
public class UploadService implements IUploadService {
    public static String url;
    @Override
    public String upload(MultipartFile picFile) throws IOException {
        //得到文件的原始文件名
        String fileName = picFile.getOriginalFilename();
        //得到文件名的后缀部分    abc.jpg     .jpg
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //得到唯一的文件夹 UUID.randomUUID()得到唯一标识符 是一个字符串
        fileName = UUID.randomUUID()+suffix;
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String datePath=dateFormat.format(new Date());
        //得到文件完整路径
        String filepath=datePath+"/"+fileName;
        //把文件保存到filePath这个路径
        String newFileName= OssUtils.upload(picFile.getInputStream(),filepath);
        //把新文件名响应出去
        log.debug("新文件名="+newFileName);
        url=newFileName;
        return newFileName;
    }
}
