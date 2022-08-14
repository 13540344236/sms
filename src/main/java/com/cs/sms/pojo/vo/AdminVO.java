package com.cs.sms.pojo.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdminVO implements Serializable {
    /**
     * 员工工号
     */
    @ExcelIgnore
    private Long id;

    /**
     * 员工姓名
     */
    @ExcelProperty("姓名")
    private String staffName;

    /**
     * 员工性别
     */
    @ExcelProperty("性别")
    private String gender;

    /**
     * 员工电话号码
     */
    @ExcelProperty("电话号码")
    private String phone;

    /**
     * 身份证号
     */
    @ExcelProperty("身份证号")
    private String idNumber;

    /**
     * 是否在岗
     */
    @ExcelProperty("是否在岗")
    private Byte onDuty;

    /**
     * 邮箱
     */
    @ExcelProperty("邮箱")
    private String email;

    /**
     *描述
     */
    @ExcelIgnore
    private String description;
    /**
     * '是否启用，1=启用，0=未启用
     */
    @ExcelProperty("是否启用")
    private Byte enable;
    /**
     * 最后登录IP地址（冗余）
     */
    @ExcelIgnore
    private String lastLoginIp;
    /**
     * 累计登录次数（冗余）
     */
    @ExcelIgnore
    private Integer loginCount;
    /**
     * 最后登录时间（冗余）
     */
    @ExcelIgnore
    private Date gmtLastLogin;
    /**
     * 数据创建时间
     */
    @ExcelIgnore
    private Date gmtCreate;
    /**
     * 数据最后修改时间
     */
    @ExcelIgnore
    private Date gmtModified;


}