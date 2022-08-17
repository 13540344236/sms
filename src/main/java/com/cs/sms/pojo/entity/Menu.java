package com.cs.sms.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Menu implements Serializable {
    /**
     * 菜单id
     */
    private Integer id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 路径
     */
    private String path;
    /**
     * 图标
     */
    private String icon;
    /**
     * 描述
     */
    private String description;
    /**
     * 父级id
     */
    private Integer pid;
    /**
     * 页面路径
     */
    private String pagePath;
    /**
     * 排序
     */
    private Integer sortNum;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<Menu> children;
}
