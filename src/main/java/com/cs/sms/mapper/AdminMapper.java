package com.cs.sms.mapper;

import com.cs.sms.pojo.entity.Admin;
import com.cs.sms.pojo.vo.AdminVO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminMapper {
    /**
     * 新增员工
     * @param admin
     * @return
     */
    int insert(Admin admin);

    /**
     * 根据id删除员工
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据id修改员工信息，传入的参数应该封装了需要修改的字段值，保持为null的属性对应的字段将不会被修改，注意：必须封装id属性
     *
     * @param admin 封装了新的值的对象
     * @return 受影响的行数，当修改成功时，将返回1，如果无此id对应的数据，将返回0
     */
    int updateById(Admin admin);

    /**
     * 根据ID查询员工信息
     * @param id 员工id
     * @return 查询成功时返回查询到的数据
     */
    AdminVO selectById(Long id);

    /**
     * 根据员工姓名查询员工信息
     * @param name 员工姓名
     * @return 查询成功时返回查询到的数据
     */
    AdminVO selectByName(String name);

    /**
     * 查询所以员工信息
     * @return 查询成功返回所以员工信息
     */
    List<AdminVO> list();

    int ExcelInsert(Admin admin);

}