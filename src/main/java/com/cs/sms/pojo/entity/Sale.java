package com.cs.sms.pojo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Sale {
    private Long id;

    private String name;

    private String goodsSpecification;

    private Integer saleQuantity;
    /**
     *  采购价格
     */
    private BigDecimal purchasePrice;

    /**
     * 销售价格
     */
    private BigDecimal salePrice;

    private String customerName;

    private String customerPhone;

    private String operatingStaff;

    private String purchaseDocumentPicture;

    private String logo;

    private Date gmtCreate;

    private Date gmtModified;

}