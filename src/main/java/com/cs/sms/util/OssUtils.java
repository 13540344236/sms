package com.cs.sms.util;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.InputStream;

/**
 * 阿里云对象储存工具类
 */
public class OssUtils {

    //地域节点
    private static final String endpoint = "oss-cn-chengdu.aliyuncs.com";

    //阿里云账号AccessKey
    private static final String accessKeyId = "LTAI5t8stP58EWDLTpYAAiE2";

    private static final String accessKeySecret = "Sp9sRf2PRWI9McVKxfu4QMNMvUqgQd";

    //bucket名称
    private static final String bucketName = "cs-sms";

    public static String upload(InputStream inputStream,String fileUrl){
        OSS ossClient=null;
        try {
            //创建OSSClient实例
            ossClient=new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);

            //储存对象
            ossClient.putObject(bucketName,fileUrl,inputStream);

        }finally {
            if(ossClient != null){
                //关闭OSSClient
                ossClient.shutdown();
            }
        }
        // 新文件名=https://sms-sc.oss-cn-chengdu.aliyuncs.com/2022-08-11/b3d177b9-3bd0-4d1e-8df0-49cee07e5115.jpg
        return "https://"+bucketName+"."+endpoint+"/"+fileUrl;
    }

}
