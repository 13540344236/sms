package com.cs.sms.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Brand {
    private Long id;

    private String name;

    private String pinyin;

    private String logo;

    private String description;

    private String keywords;

    private Byte sort;

    private Integer sales;

    private Integer productCount;

    private Byte enable;

    private Date gmtCreate;

    private Date gmtModified;

}