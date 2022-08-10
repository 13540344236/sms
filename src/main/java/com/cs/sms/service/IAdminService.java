package com.cs.sms.service;

import com.cs.sms.pojo.dto.AdminDTO;
import com.cs.sms.pojo.vo.AdminVO;

import java.util.List;

public interface IAdminService {
    /**
     * 增加员工
     * @param adminDTO
     */
    void addNew(AdminDTO adminDTO);

    /**
     * 根据ID删除对应数据
     * @param id 员工id
     */
    void deleteById(Long id);

    /**
     * 根据id修改员工信息
     * @param adminDTO
     */
    void updateById(AdminDTO adminDTO);

    /**
     * 根据id查询到的员工信息
     * @return
     */
    AdminVO selectById(Long id);

    /**
     * 根据员工姓名查询相关数据
     * @param staffName 员工姓名
     * @return
     */
    AdminVO selectByName(String staffName);

    /**
     * 查询所有员工信息
     * @return
     */
    List<AdminVO> list();
}