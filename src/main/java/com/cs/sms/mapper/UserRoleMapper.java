package com.cs.sms.mapper;


import com.cs.sms.pojo.entity.UserRole;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface UserRoleMapper {
    /**
     * 插入管理员与角色的关联数据
     * @param userRole 管理员与角色的关联数据
     * @return 受影响的行数
     */
    int insert(UserRole userRole);
}
