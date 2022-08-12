package com.cs.sms.service;

import com.cs.sms.pojo.dto.RoleDTO;
import com.cs.sms.pojo.entity.Role;
import com.cs.sms.pojo.vo.RoleVO;

import java.util.List;

public interface IRoleService {

    /**
     * 增加角色
     * @param roleDTO
     */
    void addNew(RoleDTO roleDTO);

    /**
     * 查询所有角色
     * @return
     */
    List<RoleVO> list();

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    void delete(Long id);

    /**
     * 根据id修改角色信息
     * @param role
     */
    void updateById(Role role);
}
