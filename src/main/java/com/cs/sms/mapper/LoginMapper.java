package com.cs.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cs.sms.pojo.dto.LoginPasswordDTO;
import com.cs.sms.pojo.entity.Login;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**

 */
public interface LoginMapper extends BaseMapper<Login> {

    @Update("update sms_user set password = #{newPassword} where username = #{username} and password = #{password}")
    int updatePassword(LoginPasswordDTO loginPasswordDTO);

    Page<Login> findPage(Page<Login> page, @Param("username") String username, @Param("email") String email, @Param("address") String address);
}
