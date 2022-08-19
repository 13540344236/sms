package com.cs.sms.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class GoodSCategory implements Serializable {

    private Long id;

    private Long goodsId;

    private Long categoryId;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
