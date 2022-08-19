package com.cs.sms.controller;
import cn.hutool.core.util.StrUtil;
import com.cs.sms.pojo.dto.LoginDTO;
import com.cs.sms.service.impl.LoginServiceImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 处理登录、注册、修改密码
 */
@CrossOrigin
@Slf4j
@Api(tags = "登录模块")
@RestController
@RequestMapping("/logins")
public class LoginController {

    @Resource
    private LoginServiceImpl loginService;

    @PostMapping("/login")
    public boolean login(@RequestBody LoginDTO loginDTO){
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        log.debug("username{}",username);
        log.debug("password{}",password);
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return false;
        }
        return loginService.login(loginDTO);
    }

}
