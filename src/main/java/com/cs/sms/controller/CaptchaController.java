package com.cs.sms.controller;
/**
 * 图形验证码
 */

import com.cs.sms.web.CommonResult;
import com.cs.sms.web.Captcha;
import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@CrossOrigin
@Slf4j
@Api(tags = "登录验证码模块")
@RestController
@RequestMapping("/captchas")
public class CaptchaController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 生成验证码
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping("/captcha")
    public CommonResult captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("生成验证码请求");
        // png类型
        SpecCaptcha captcha = new SpecCaptcha(130, 48, 5);

        //设置验证码样式
        captcha.setCharType(Captcha.TYPE_DEFAULT);

        //验证码 VUL
        String verCode = captcha.text().toLowerCase();
        log.info("验证码:{}",verCode);
        //uuid KEY
        String key ="captcha:"+ UUID.randomUUID();
        log.info("KEY:{}",key);
        // 存入redis并设置过期时间为30分钟
        stringRedisTemplate.opsForValue().set(key, verCode, 30, TimeUnit.MINUTES);

        // 将key和base64返回给前端
        Map<String,String> map=new HashMap<>();
        map.put("key", key);
        map.put("image", captcha.toBase64());
        return new CommonResult(200,"success",map);


    }

    /**00
     * 验证码验证
     * @param verCode  验证码
     * @param verKey   redis验证码key
     * @return
     */
    @ResponseBody
    @PostMapping("/verification")
    public Boolean verification(String verCode,String verKey){
        // 获取redis中的验证码
        String redisCode = stringRedisTemplate.opsForValue().get(verKey);
        // 判断验证码
        if (verCode==null || !Objects.equals(redisCode, verCode.trim().toLowerCase())) {
            return false;
        }
        return true;
    }
}
