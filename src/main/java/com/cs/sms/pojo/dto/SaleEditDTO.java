package com.cs.sms.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 编辑销售的DTO类
 */
@Data
public class SaleEditDTO implements Serializable {
    /**
     * 记录id
     */
    private Long id;

    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品规格
     */
    private String goodsSpecification;
    /**
     * 销售数量
     */
    private Integer saleQuantity;
    /**
     *  采购价格
     */
    private BigDecimal purchasePrice;

    /**
     * 销售价格
     */
    private BigDecimal salePrice;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 客户电话
     */
    private String customerPhone;
    /**
     * 操作员工
     */
    private String operatingStaff;
    /**
     * 进货单据
     */
    private String purchaseDocumentPicture;
    /**
     * 商品logo
     */
    private String logo;
}
