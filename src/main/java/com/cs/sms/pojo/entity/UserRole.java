package com.cs.sms.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 管理员与角色的关联数据的实体类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Data
public class UserRole implements Serializable {

    /**
     * 数据id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 数据创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    private LocalDateTime gmtModified;

}
