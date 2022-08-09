package com.cs.sms.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminDTO implements Serializable {
    /**
     * 员工id
     */
    private Long id;
    /**
     * 员工姓名
     */
    private String staffName;
    /**
     * 员工密码
     */
    private String password;
    /**
     * 员工性别
     */
    private String gender;
    /**
     * 员工电话号码
     */
    private String phone;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 描述
     */
    private String description;

}