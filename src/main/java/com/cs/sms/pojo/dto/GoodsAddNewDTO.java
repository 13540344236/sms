package com.cs.sms.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class GoodsAddNewDTO implements Serializable {
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "品牌名称",required = true)
    private String name;

    /**
     *  图片地址
     */
    @ApiModelProperty("商品图片的url")
    private String url;

    /**
     * 商品类别
     */
    @ApiModelProperty("商品类别")
    private String category;

    /**
     *  采购价格
     */
    @ApiModelProperty("采购价格")
    private BigDecimal purchasePrice;

    /**
     * 销售价格
     */
    @ApiModelProperty("销售价格")
    private BigDecimal salePrice;

    /**
     * 商品规格
     */
    @ApiModelProperty("商品规格")
    private String goodsSpecification;

    /**
     * 当前库存
     */
    @ApiModelProperty("当前库存")
    private Long currentStock;

    /**
     * 库存下限
     */
    @ApiModelProperty("库存下限")
    private Long lowLimitStock;
}
