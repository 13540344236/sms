package com.cs.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.sms.pojo.dto.UserDTO;
import com.cs.sms.pojo.dto.UserPasswordDTO;
import com.cs.sms.pojo.entity.User;


/**
 *  服务类
 */
public interface IUserService extends IService<User> {

    boolean login(UserDTO userDTO);

    User register(UserDTO userDTO);

    void updatePassword(UserPasswordDTO userPasswordDTO);

    Page<User> findPage(Page<User> objectPage, String username, String email, String address);
}
