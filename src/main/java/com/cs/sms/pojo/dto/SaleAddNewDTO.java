package com.cs.sms.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 添加销售的DTO类
 */
@Data
public class SaleAddNewDTO implements Serializable {
    @ApiModelProperty(value = "商品id")
    private Long id;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", required = true, example = "脉动")
    @NotBlank(message = "请填写有效的商品名称")
    private String name;

    /**
     * 商品规格
     */
    private String goodsSpecification;
    /**
     * 销售数量
     */
    @ApiModelProperty(value = "商品规格", required = true, example = "瓶")
    private Integer saleQuantity;
    /**
     * 客户姓名
     */
    @ApiModelProperty(value = "客户姓名",required = true,example = "王先生")
    private String customerName;
    /**
     * 客户电话
     */
    @ApiModelProperty(value = "客户电话",required = true,example = "18390902342")
    private String customerPhone;
    /**
     * 操作员工
     */
    @ApiModelProperty(value = "操作员工",required = true,example = "张三")
    private String operatingStaff;
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
    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss",timezone = "GMT+8")
    private Date gmtCreate;
    /**
     * 数据最后修改时间
     */
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss",timezone = "GMT+8")
    private Date gmtModified;
}
