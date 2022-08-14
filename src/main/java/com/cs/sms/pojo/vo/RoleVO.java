package com.cs.sms.pojo.vo;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Data
public class RoleVO implements Serializable {
    private Long id;

    private String name;

    private String description;

    private Byte sort;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

}