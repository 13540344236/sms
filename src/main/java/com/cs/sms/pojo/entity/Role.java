package com.cs.sms.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Role {
    private Long id;

    private String name;

    private String description;

    private Byte sort;

    private Date gmtCreate;

    private Date gmtModified;

}