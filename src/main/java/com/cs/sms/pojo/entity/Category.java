package com.cs.sms.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Category {
    private Long id;

    private String name;

    private Long parentId;

    private Byte depth;

    private String keywords;

    private Byte sort;

    private String icon;

    private Byte enable;

    private Byte isParent;

    private Byte isDisplay;

    private Date gmtCreate;

    private Date gmtModified;

}