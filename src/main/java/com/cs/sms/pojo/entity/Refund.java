package com.cs.sms.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 库存管理
 */
@Data
public class Refund {
    /**
     * 商品id
     */
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
     *商品的logo的URL
     */
    private String logo;
    /**
     *进货时间
     */
    private LocalDateTime gmtCreatePurchase;
    /**
     *退货时间
     */
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtCreateReturn;
    /**
     *数据最后修改时间
     */
    private LocalDateTime gmtModified;

}