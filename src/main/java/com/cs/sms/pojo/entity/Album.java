package com.cs.sms.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Album {
    private Long id;

    private String name;

    private String description;

    private Byte sort;

    private Date gmtCreate;

    private Date gmtModified;

}