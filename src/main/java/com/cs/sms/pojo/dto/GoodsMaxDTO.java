package com.cs.sms.pojo.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class GoodsMaxDTO implements Serializable {

    private Long id; //'编号'

    private String url;                 //'图片地址',

    private String name;                //'名称',

    private String category;            // '类别',

    private BigDecimal purchasePrice;      //'采购价格',

    private BigDecimal salePrice;          //'销售价格',

    private String goodsSpecification; //'规格',

    private Long currentStock;       // '当前库存',


    private Long theOverflow ;// '溢出数量',

}
