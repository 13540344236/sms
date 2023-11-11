package com.cs.sms.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class HttpUtils {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/joan");
        headers.set("Authorization","Bearer" + "此处替换为token");
        //设置请求参数
        Map<String,String> params = new HashMap<>();
        params.put("参数字段1","参数1");
        params.put("参数字段2","参数2");
        //创建httpEntity实例
        HttpEntity<Map<String,String>> requestEntity = new HttpEntity<>(params,headers);
        //发送post请求，并接受ResponseEntity<String>类型的响应
        ResponseEntity<String> response = restTemplate.exchange( "替换为请求链接", HttpMethod.POST, requestEntity, String.class);
        // 获取响应状态码和内容
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
    }
}
