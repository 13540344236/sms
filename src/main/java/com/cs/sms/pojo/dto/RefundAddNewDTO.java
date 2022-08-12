package com.cs.sms.pojo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class RefundAddNewDTO implements Serializable {
    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id",required = true)
    private Long id;
    /**
     * 商品名称
     */
    @ApiModelProperty("品牌名称")
    private String name;
    /**
     *商品类别
     */
    @ApiModelProperty("商品类别")

    private String Category;
    /**
     *商品规格
     */
    @ApiModelProperty( "商品规格")
    private String goodsSpecification;
    /**
     *退货出库数量
     */
    @ApiModelProperty("退货出库数量")
    private Integer warehousingQuantity;
    /**
     *金额
     */
    @ApiModelProperty("金额")
    private Integer amountPayable;
    /**
     *是否付款
     */
    @ApiModelProperty("是否付款")
    private Integer isPay;
    /**
     *供货商
     */
    @ApiModelProperty("供应商")
    private String supplier;
    /**
     *经办人
     */
    @ApiModelProperty("经办人")
    private String operator;
    /**
     *退货单据图片
     */
    @ApiModelProperty("退货单据图片")
    private String returnDocumentPicture;
    /**
     *商品的logo的URL
     */
    @ApiModelProperty("商品logo")
    private String logo;

    @ApiModelProperty("进货时间")
    private LocalDateTime gmtCreatePurchase;
    /**
     *退货时间
     */
    @ApiModelProperty("退货时间")
    private LocalDateTime gmtCreateReturn;
    /**
     *数据最后修改时间
     */
    @ApiModelProperty("最后修改时间")
    private LocalDateTime gmtModified;




}
