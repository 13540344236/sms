package com.cs.sms.pojo.entity;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class Goods {
    private Long id;

    private String url;

    private String name;

    private String category;

    private BigDecimal purchasePrice;

    private BigDecimal salePrice;

    private String goodsSpecification;

    private Long currentStock;

    private Long lowLimitStock;

}