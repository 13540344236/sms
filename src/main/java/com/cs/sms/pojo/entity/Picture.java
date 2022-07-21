package com.cs.sms.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Picture {
    private Long id;

    private Long albumId;

    private String url;

    private String description;

    private Short width;

    private Short height;

    private Byte isCover;

    private Byte sort;

    private Date gmtCreate;

    private Date gmtModified;

}