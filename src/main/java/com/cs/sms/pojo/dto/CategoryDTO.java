package com.cs.sms.pojo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryDTO {
    /**
     * 类别ID
     */
    private Long id;
    /**
     * 类别名
     */
    private String name;
    /**
     * 父级类别id
     */
    private Long parentId;
    /**
     * 深度
     */
    private Byte depth;
    /**
     * 关键词列表
     */
    private String keywords;
    /**
     * 自定义排序号
     */
    private Byte sort;
    /**
     * 图标图片URL
     */
    private String icon;

}