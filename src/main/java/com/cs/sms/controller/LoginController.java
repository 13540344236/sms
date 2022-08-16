//package com.cs.sms.controller;
//
//import cn.hutool.crypto.SecureUtil;
//import com.cs.sms.web.Constants;
//import com.cs.sms.web.Result;
//import com.cs.sms.pojo.dto.UserDTO;
//import com.cs.sms.pojo.dto.UserPasswordDTO;
//import com.cs.sms.pojo.entity.User;
//import com.cs.sms.service.IUserService;
//import com.github.xiaoymin.knife4j.core.util.StrUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * 处理登录、注册、修改密码
// */
//@Slf4j
//@RestController
//@RequestMapping("/logins")
//public class LoginController {
//    @Resource
//    private IUserService userService;
//
//    //登录
//    @PostMapping("/login")
//    public boolean login(@RequestBody UserDTO userDTO) {
//        log.debug("接受到前端的登录数据{}",userDTO);
//        String username = userDTO.getUsername();
//        String password = userDTO.getPassword();
//        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
//            return false;
//        }
//        return userService.login(userDTO);
//    }
//
//    @PostMapping("/register")
//    public Result register(@RequestBody UserDTO userDTO) {
//        log.debug("接受到前端的注册数据{}",userDTO);
//        String username = userDTO.getUsername();
//        String password = userDTO.getPassword();
////        校验是否为空
//        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
//            return Result.error(Constants.CODE_400, "参数错误");
//        }
//        return Result.success(userService.register(userDTO));
//    }
//
//    // 新增或者更新
//    @PostMapping
//    public Result save(@RequestBody User user) {
//        log.debug("接受到前端的新增数据{}",user);
//        if (user.getId() == null && user.getPassword() == null) {  // 新增用户默认密码
//
//            user.setPassword(SecureUtil.md5("123"));
//        }
//        return Result.success(userService.saveOrUpdate(user));
//    }
//
//    /**
//     * 修改密码
//     */
//    @PostMapping("/password")
//    public Result password(@RequestBody UserPasswordDTO userPasswordDTO) {
//        log.debug("接受到前端的修改密码数据{}",userPasswordDTO);
//        userPasswordDTO.setPassword(SecureUtil.md5(userPasswordDTO.getPassword()));
//        userPasswordDTO.setNewPassword(SecureUtil.md5(userPasswordDTO.getNewPassword()));
//        userService.updatePassword(userPasswordDTO);
//        return Result.success();
//    }
//
//}
