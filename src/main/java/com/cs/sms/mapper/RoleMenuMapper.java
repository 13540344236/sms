package com.cs.sms.mapper;

import com.cs.sms.pojo.entity.RoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuMapper {

    int deleteByRoleId(Integer roleId);

    int insert(RoleMenu roleMenu);

    List<RoleMenu> selectByRoleId(Integer roleId);
}
