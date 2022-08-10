package com.cs.sms.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * 编辑商品的DTO类
 */
@Data
public class PurchaseEditDTO implements Serializable {
    /**
     * 记录id
     */
    @ApiModelProperty(value = "商品id", required = true, example = "0")
    private Long id;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", required = true, example = "脉动")
    private String name;
    /**
     * 商品类别
     */
    @ApiModelProperty(value = "商品类别", required = true, example = "饮料")
    private String goodsCategory;
    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格", required = true, example = "瓶")
    private String goodsSpecification;
    /**
     * 入库数量
     */
    @ApiModelProperty(value = "入库数量", required = true, example = "1")
    private Integer warehousingQuantity;
    /**
     * 供应商
     */
    @ApiModelProperty(value = "供应商", required = true, example = "王先生")
    private String supplier;
    /**
     * 经办人
     */
    @ApiModelProperty(value = "经办人", required = true, example = "最高管理员")
    private String operator;
    /**
     * 进货单据
     */
    @ApiModelProperty(value = "进货单据图片", example = "http://www.Document.com/danju.png")
    private String purchaseDocumentPicture;
    /**
     * 商品logo
     */
    @ApiModelProperty(value = "商品logo的URL", example = "http://www.logo.com/maidong.png")
    private String logo;
}
