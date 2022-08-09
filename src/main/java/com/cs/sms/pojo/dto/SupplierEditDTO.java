package com.cs.sms.pojo.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class SupplierEditDTO implements Serializable {
    private Long id;

    private String supplier;

    private String supplierName;

    private String supplierPhone;

    private String supplierSite;

    private String remark;

}
