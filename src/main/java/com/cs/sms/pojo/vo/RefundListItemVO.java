package com.cs.sms.pojo.vo;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class RefundListItemVO implements Serializable {
    /**
     * 商品id
     */
    @ExcelProperty("商品id")
    private Long id;
    /**
     * 商品名称
     */
    @ExcelProperty("商品名称")
    private String name;
    /**
     *商品类别
     */
    @ExcelProperty("商品类别")
    private String Category;
    /**
     *商品规格
     */
    @ExcelProperty("商品规格")
    private String goodsSpecification;
    /**
     *退货出库数量
     */
    @ExcelProperty("退货出库数量")
    private Integer warehousingQuantity;
    /**
     *金额
     */
    @ExcelProperty("金额")
    private Integer amountPayable;
    /**
     *是否付款
     */
    @ExcelProperty("是否付款")
    private Integer isPay;
    /**
     *供货商
     */
    @ExcelProperty("供货商")
    private String supplier;
    /**
     *经办人
     */
    @ExcelProperty("经办人")
    private String operator;
    /**
     *退货单据图片
     */
    @ExcelProperty("退货单据图片")
    private String returnDocumentPicture;
    /**
     *商品的logo的URL
     */
    @ExcelProperty("商品logo")
    private String logo;
    /**
     *商品的进货时间
     */
    @ExcelProperty("商品进货时间")
    private LocalDateTime gmtCreatePurchase;
    /**
     *退货时间
     */
    @ExcelProperty("退货时间")
    private LocalDateTime gmtCreateReturn;
    /**
     *数据最后修改时间
     */
    @ExcelProperty("最后修改时间")
    private LocalDateTime gmtModified;
}
