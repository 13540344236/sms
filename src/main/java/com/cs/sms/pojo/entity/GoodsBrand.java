package com.cs.sms.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class GoodsBrand {
    private Long id;

    private Long goodsId;

    private Long brandId;

    private Date gmtCreate;

    private Date gmtModified;

}