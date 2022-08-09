package com.cs.sms.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PurchaseListItemVO implements Serializable {
    private Long id;

    private String name;

    private String goodsCategory;

    private String goodsSpecification;

    private Integer warehousingQuantity;

    private String supplier;

    private String operator;

    private String purchaseDocumentPicture;

    private String logo;

    @JsonFormat(pattern = "yyyy年MM月dd日 HH点mm分ss秒",timezone = "GMT+8")
    private LocalDateTime gmtCreate;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH点mm分ss秒",timezone = "GMT+8")
    private LocalDateTime gmtModified;
}
