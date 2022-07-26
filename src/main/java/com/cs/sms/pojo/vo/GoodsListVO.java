package com.cs.sms.pojo.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class GoodsListVO implements Serializable {
    /**
     * 商品id
     */
    @ExcelIgnore //导出excel时候不导出id
    private Long id;

    /**
     * 商品名称
     */
    @ExcelProperty("商品名称")
    private String name;

    /**
     *  图片地址
     */
    @ExcelIgnore  // 加上此注解可以忽视这个字段的excel导出
    private String url;

    /**
     * 商品类别
     */
    @ExcelProperty("商品类别")
    private String category;

    /**
     *  采购价格
     */
    @ExcelProperty("采购价格")
    private BigDecimal purchasePrice;

    /**
     * 销售价格
     */
    @ExcelProperty("销售价格")
    private BigDecimal salePrice;

    /**
     * 商品规格
     */
    @ExcelProperty("商品规格")
    private String goodsSpecification;

    /**
     * 当前库存
     */
    @ExcelProperty("当前库存")
    private Long currentStock;

    /**
     * 库存下限
     */
    @ExcelProperty("库存下限")
    private Long lowLimitStock;
}
