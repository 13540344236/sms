package com.cs.sms.pojo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleMenu implements Serializable {


    private Integer id;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 菜单id
     */
    private Integer menuId;
}
