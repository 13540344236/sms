package com.cs.sms.controller;

import cn.hutool.crypto.SecureUtil;
import com.cs.sms.common.Constants;
import com.cs.sms.common.Result;
import com.cs.sms.pojo.dto.UserDTO;
import com.cs.sms.pojo.dto.UserPasswordDTO;
import com.cs.sms.pojo.entity.User;
import com.cs.sms.service.IUserService;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;



/**
 *  登录相关
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private IUserService userService;

    //登录
    @PostMapping("/login")
    public boolean login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return false;
        }
        return userService.login(userDTO);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
//        校验是否为空
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.register(userDTO));
    }

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody User user) {
        if (user.getId() == null && user.getPassword() == null) {  // 新增用户默认密码

            user.setPassword(SecureUtil.md5("123"));
        }
        return Result.success(userService.saveOrUpdate(user));
    }

    /**
     * 修改密码
     */
    @PostMapping("/password")
    public Result password(@RequestBody UserPasswordDTO userPasswordDTO) {
        userPasswordDTO.setPassword(SecureUtil.md5(userPasswordDTO.getPassword()));
        userPasswordDTO.setNewPassword(SecureUtil.md5(userPasswordDTO.getNewPassword()));
        userService.updatePassword(userPasswordDTO);
        return Result.success();
    }
}

