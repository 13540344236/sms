package com.cs.sms.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * 商品列表详情VO
 */
@Data
public class PurchaseListItemVO implements Serializable {
    /**
     * 记录id
     */
    @ExcelProperty("商品id")
    private Long id;
    /**
     * 商品名称
     */
    @ExcelProperty("商品名称")
    private String name;
    /**
     * 商品类别
     */
    @ExcelProperty("商品类别")
    private String goodsCategory;
    /**
     * 商品规格
     */
    @ExcelProperty("商品规格")
    private String goodsSpecification;
    /**
     * 入库数量
     */
    @ExcelProperty("入库数量")
    private Integer warehousingQuantity;
    /**
     * 供应商
     */
    @ExcelProperty("供应商")
    private String supplier;
    /**
     * 经办人
     */
    @ExcelProperty("经办人")
    private String operator;
    /**
     * 进货单据
     */
    @ExcelProperty("进货单据")
    private String purchaseDocumentPicture;
    /**
     * 商品logo
     */
    @ExcelProperty("商品logo")
    private String logo;
    /**
     * 操作时间
     */
    @ExcelProperty("操作时间")
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss",timezone = "GMT+8")
    private Date gmtCreate;
    /**
     * 数据最后修改时间
     */
    @ExcelProperty("修改时间")
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss",timezone = "GMT+8")
    private Date gmtModified;
}
