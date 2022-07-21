package com.cs.sms.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Purchase {
    private Long id;

    private String name;

    private String goodsCategory;

    private String goodsSpecification;

    private Integer warehousingQuantity;

    private String supplier;

    private String operator;

    private String purchaseDocumentPicture;

    private String logo;

    private Date gmtCreate;

    private Date gmtModified;

}