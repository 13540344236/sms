package com.cs.sms.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class LoginLog {
    private Long id;

    private Long adminId;

    private String username;

    private String nickname;

    private String ip;

    private String userAgent;

    private Date gmtLogin;

    private Date gmtCreate;

    private Date gmtModified;

}