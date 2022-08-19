package com.cs.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.sms.mapper.LoginMapper;
import com.cs.sms.pojo.dto.LoginDTO;
import com.cs.sms.pojo.dto.LoginPasswordDTO;
import com.cs.sms.pojo.entity.Login;
import com.cs.sms.service.ILoginService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;



/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements ILoginService {


    @Override
    public boolean login(LoginDTO loginDTO) {
        QueryWrapper<Login>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username", loginDTO.getUsername());
        queryWrapper.eq("password",loginDTO.getPassword());
        Login one=getOne(queryWrapper);
        return one !=null;
    }


}
