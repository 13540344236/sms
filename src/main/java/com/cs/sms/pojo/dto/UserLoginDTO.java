package com.cs.sms.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class UserLoginDTO implements Serializable {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 密码（密文）
     */
    @ApiModelProperty(value = "密码")
    private String password;
}
