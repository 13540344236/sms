package com.cs.sms.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 销售商品列表详情VO
 */
@Data
public class SaleListItemVO implements Serializable {
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
     * 商品规格
     */
    @ExcelProperty("商品规格")
    private String goodsSpecification;
    /**
     * 销售数量
     */
    @ExcelProperty("销售数量")
    private Integer saleQuantity;
    /**
     * 客户姓名
     */
    @ExcelProperty("客户姓名")
    private String customerName;
    /**
     * 客户电话
     */
    @ExcelProperty("客户电话")
    private String customerPhone;
    /**
     * 操作员工
     */
    @ExcelProperty("操作员工")
    private String operatingStaff;

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
    @ExcelProperty("数据最后修改时间")
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss",timezone = "GMT+8")
    private Date gmtModified;
}
