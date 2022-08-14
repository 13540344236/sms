package com.cs.sms.pojo.entity;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
@Data
public class Role implements Serializable {
    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色姓名
     */
    private String name;

    /**
     *角色描述
     */
    private String description;

    /**
     * 角色序号
     */
    private Byte sort;

    /**
     * 角色创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 角色最后修改时间
     */
    private LocalDateTime gmtModified;

}