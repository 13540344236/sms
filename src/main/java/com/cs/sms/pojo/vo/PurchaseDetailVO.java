package com.cs.sms.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 进货详情VO
 */
@Data
public class PurchaseDetailVO implements Serializable {
    private Long id;

    private String name;

    private String goodsCategory;

    private String goodsSpecification;

    private Integer warehousingQuantity;

    private String supplier;

    private String operator;

    private String purchaseDocumentPicture;

    private String logo;
}
