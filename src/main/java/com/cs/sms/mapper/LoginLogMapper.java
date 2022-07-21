package com.cs.sms.mapper;

import com.cs.sms.pojo.entity.LoginLog;

public interface LoginLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);
}