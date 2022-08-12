package com.cs.sms.pojo.entity;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class GoodsBad implements Serializable {

    private Long id; //'编号'

    private String url;                 //'图片地址',

    private String name;                //'名称',

    private String category;            // '类别',

    private BigDecimal purchasePrice;      //'采购价格',

    private BigDecimal salePrice;          //'销售价格',

    private String goodsSpecification; //'规格',

    private Long currentStock;       // '当前库存',

    private Long lowLimitStock;     //'库存下限'

    private Long reportedLoss ;// '损坏数量',

}
