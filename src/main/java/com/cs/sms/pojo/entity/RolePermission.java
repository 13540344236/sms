package com.cs.sms.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class RolePermission {
    private Long id;

    private Long roleId;

    private Long permissionId;

    private Date gmtCreate;

    private Date gmtModified;

}