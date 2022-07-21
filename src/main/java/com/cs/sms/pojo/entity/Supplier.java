package com.cs.sms.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Supplier {
    private Long id;

    private String supplier;

    private String supplierName;

    private String supplierPhone;

    private String supplierSite;

    private String remark;

    private Date gmtCreate;

    private Date gmtModified;

}