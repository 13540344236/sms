package com.cs.sms.pojo.vo;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 接受前端登录请求的参数
 */
@Data
public class UserVO implements Serializable {
    @ApiModelProperty("id")
    @ExcelIgnore
    private Integer id;

    @ExcelProperty("用户名")
    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    @ExcelIgnore
    private String password;

    @ApiModelProperty("昵称")
    @ExcelProperty("昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    @ExcelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话")
    @ExcelProperty("电话")
    private String phone;

    @ApiModelProperty("地址")
    @ExcelProperty("地址")
    private String address;

    @ApiModelProperty("创建时间")
    @ExcelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("头像")
    private String avatarUrl;

    @ApiModelProperty("角色")
    @ExcelProperty("角色")
    private String role;

    /**
     * '是否启用，1=启用，0=未启用
     */
    @ExcelProperty("是否启用")
    private Byte enable;

}
