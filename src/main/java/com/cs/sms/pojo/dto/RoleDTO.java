package com.cs.sms.pojo.dto;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
@Data
public class RoleDTO implements Serializable {

    private Long id;

    private String name;

    private String description;

    private Byte sort;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}