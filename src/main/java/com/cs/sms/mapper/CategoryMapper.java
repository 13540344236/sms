package com.cs.sms.mapper;

import com.cs.sms.pojo.entity.Admin;
import com.cs.sms.pojo.entity.Category;
import com.cs.sms.pojo.vo.AdminVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    /**
     * 新增类别
     * @param category
     * @return
     */
    int insert(Category category);

    /**
     * 根据id删除类别
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据id修改类别信息
     *
     * @param  category 封装了新的值的对象
     * @return 受影响的行数，当修改成功时，将返回1，如果无此id对应的数据，将返回0
     */
    int updateById(Category category);

    /**
     * 根据ID查询类别信息
     * @param id 类别id
     * @return 查询成功时返回查询到的数据
     */
    Category selectById(Long id);

    /**
     * 根据员工姓名查询类别信息
     * @param name 类别名
     * @return 查询成功时返回查询到的数据
     */
    Category selectByName(String name);

    /**
     * 查询所以类别信息
     * @return 查询成功返回所以类别信息
     */
    List<Category> list();

    /**
     * 分页查询所有员工
     * @return 员工列表
     */
    List<Category> findAllCategory();
}