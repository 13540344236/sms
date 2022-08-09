package com.cs.sms.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * 编辑商品的DTO类
 */
@Data
public class PurchaseEditDTO implements Serializable {
    @ApiModelProperty(value = "商品id", required = true, example = "0")
    private Long id;
    @ApiModelProperty(value = "商品名称", required = true, example = "脉动")
    private String name;
    @ApiModelProperty(value = "商品类别", required = true, example = "饮料")
    private String goodsCategory;
    @ApiModelProperty(value = "商品规格", required = true, example = "瓶")
    private String goodsSpecification;
    @ApiModelProperty(value = "入库数量", required = true, example = "1")
    private Integer warehousingQuantity;
    @ApiModelProperty(value = "供应商", required = true, example = "王先生")
    private String supplier;
    @ApiModelProperty(value = "经办人", required = true, example = "最高管理员")
    private String operator;
    @ApiModelProperty(value = "进货单据图片", example = "http://www.Document.com/danju.png")
    private String purchaseDocumentPicture;
    @ApiModelProperty(value = "商品logo的URL", example = "http://www.logo.com/maidong.png")
    private String logo;
}
