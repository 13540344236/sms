package com.cs.sms.pojo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 接受前端登录请求的参数
 */
@Data
public class UserDTO implements Serializable {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("头像")
    private String avatarUrl;

    @ApiModelProperty("角色")
    private String role;


    /**
     * '是否启用，1=启用，0=未启用
     */
    private Byte enable;

}
