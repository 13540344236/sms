package com.cs.sms.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Admin {
    private Long id;

    private String staffName;

    private String password;

    private String gender;

    private String phone;

    private String idNumber;

    private Byte onDuty;

    private String email;

    private String description;

    private Byte enable;

    private String lastLoginIp;

    private Integer loginCount;

    private Date gmtLastLogin;

    private Date gmtCreate;

    private Date gmtModified;

}