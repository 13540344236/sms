package com.cs.sms.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class BrandCategory {
    private Long id;

    private Long brandId;

    private Long categoryId;

    private Date gmtCreate;

    private Date gmtModified;

}