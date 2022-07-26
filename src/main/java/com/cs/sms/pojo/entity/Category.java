package com.cs.sms.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Category {
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
    /**
     * 是否启用,1=启用,0=未启用
     */
    private Byte enable;
    /**
     * 是否为父级,1=父级,0=不是父级
     */
    private Byte isParent;
    /**
     * 是否显示导航栏,1=启用,0=未启用
     */
    private Byte isDisplay;
    /**
     * 数据创建时间
     */
    private Date gmtCreate;
    /**
     * 数据最后修改时间
     */
    private Date gmtModified;

}