package com.cs.sms.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class Goods implements Serializable {
    /**
     *  编号
     */
    private Long id;

    /**
     *  图片地址
     */
    private String url;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品类别
     */
    private String category;

    /**
     *  采购价格
     */
    private BigDecimal purchasePrice;

    /**
     * 销售价格
     */
    private BigDecimal salePrice;

    /**
     * 商品规格
     */
    private String goodsSpecification;

    /**
     * 当前库存
     */
    private Long currentStock;

    /**
     * 库存下限
     */
    private Long lowLimitStock;

}