package com.cs.sms.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Sale {
    private Long id;

    private String name;

    private String goodsSpecification;

    private Integer saleQuantity;

    private String customerName;

    private String customerPhone;

    private String operatingStaff;

    private String purchaseDocumentPicture;

    private String logo;

    private Date gmtCreate;

    private Date gmtModified;

}