package com.cs.sms.controller;
/**
 * 短信验证码
 */


import com.cs.sms.util.ClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/sms")
public class SmsController {


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ClientUtil smsClient;

    @GetMapping("/sendSmsCode")
    public  Map<String,Object>sendSmsCode(@RequestParam("phoneNumber") String phoneNumber) {
        Map<String, Object> result = new HashMap<>();
        //生成随机验证码
        String smsCode = (int) ((Math.random() * 9 + 1) * 10000) + "";
        log.info("短信验证码:{}",smsCode);
        try {
            //获取操作器存入redis并设置手机号过期时间3分钟
            stringRedisTemplate.opsForValue().set(phoneNumber, smsCode, 3);

            //传给smsClient 参数 (phoneNumber,smsCode)
            smsClient.sendSmsCode(phoneNumber, smsCode);
            result.put("code",1);
            result.put("msg", "短信发送成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("code",2);
        result.put("msg", "短信发送失败");
        return result;
    }
        @GetMapping("/checkSmsCode") //判断验证码是否正确
        public Map<String, Object> checkSmsCode (@RequestParam("phoneNumber") String phoneNumber, @RequestParam("smsCode") String smsCode) {
            Map<String, Object> result = new HashMap<>();
            String cacheCode = stringRedisTemplate.opsForValue().get(phoneNumber);
            if (cacheCode !=null && cacheCode.equals(smsCode)){
                stringRedisTemplate.delete(phoneNumber);
                result.put("code",1);
                result.put("msg","效验短信成功");
                return result;
            }
            result.put("code",2);
            result.put("msg","效验短信失败");
            return result;
    }

}