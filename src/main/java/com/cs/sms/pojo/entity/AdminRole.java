package com.cs.sms.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class AdminRole {
    private Long id;

    private Long adminId;

    private Long roleId;

    private Date gmtCreate;

    private Date gmtModified;

}