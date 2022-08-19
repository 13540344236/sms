package com.cs.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cs.sms.pojo.dto.LoginDTO;
import com.cs.sms.pojo.dto.LoginPasswordDTO;
import com.cs.sms.pojo.entity.Login;
import org.springframework.data.domain.Page;

/**
 *  服务类
 */
public interface ILoginService extends IService<Login> {

    boolean login(LoginDTO userDTO);


}
