package com.cs.sms.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RefundEditDTO {
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     *商品类别
     */
    private String Category;
    /**
     *商品规格
     */
    private String goodsSpecification;
    /**
     *退货出库数量
     */
    private Integer warehousingQuantity;
    /**
     *金额
     */
    private Integer amountPayable;
    /**
     *是否付款
     */
    private Integer isPay;
    /**
     *供货商
     */
    private String supplier;
    /**
     *经办人
     */
    private String operator;
    /**
     *退货单据图片
     */
    private String returnDocumentPicture;
    /**
     *
     */
    private String logo;

    private LocalDateTime gmtCreatePurchase;
    /**
     *退货时间
     */
    private LocalDateTime gmtCreateReturn;
    /**
     *数据最后修改时间
     */
    private LocalDateTime gmtModified;
}
