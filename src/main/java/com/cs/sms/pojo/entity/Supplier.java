package com.cs.sms.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Supplier implements Serializable {

    private Long id;

    private String supplier;

    private String supplierName;

    private String supplierPhone;

    private String supplierSite;

    private String remark;

    private Date gmtCreate;

    private Date gmtModified;

}