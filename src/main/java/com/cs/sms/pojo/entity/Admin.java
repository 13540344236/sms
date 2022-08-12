package com.cs.sms.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class Admin {
    /**
     * 员工工号
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
     * 是否在岗
     */
    private Byte onDuty;
    /**
     * 邮箱
     */
    private String email;
    /**
     *描述
     */
    private String description;
    /**
     * '是否启用，1=启用，0=未启用
     */
    private Byte enable;
    /**
     * 最后登录IP地址（冗余）
     */
    private String lastLoginIp;
    /**
     * 累计登录次数（冗余）
     */
    private Integer loginCount;
    /**
     * 最后登录时间（冗余）
     */
    private Date gmtLastLogin;
    /**
     * 数据创建时间
     */
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss",timezone = "GMT+8")
    private Date gmtCreate;
    /**
     * 数据最后修改时间
     */
    private Date gmtModified;

}