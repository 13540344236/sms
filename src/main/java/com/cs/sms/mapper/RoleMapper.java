package com.cs.sms.mapper;

import com.cs.sms.pojo.dto.RoleDTO;
import com.cs.sms.pojo.entity.Role;
import com.cs.sms.pojo.vo.RoleVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    /**
     * 添加角色
     * @param role
     * @return
     */
    int insert(Role role);

    /**
     * 根据名字查找角色
     * @param name
     * @return
     */
    int select(String name);

    /**
     * 查询所有角色信息
     * @return
     */
    List<RoleVO> list();

    /**
     * 根据ID删除角色
     * @param id
     * @return
     */
    int deleteById(Long... id);

    /**
     * 根据ID查询角色
     * @param id
     * @return
     */
    List<RoleVO> getById(Long id);

    /**
     * 根据ID修改角色信息
     * @param role
     * @return
     */
    int updateById(Role role);
}