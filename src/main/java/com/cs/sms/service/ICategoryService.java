package com.cs.sms.service;


import com.cs.sms.pojo.dto.AdminDTO;
import com.cs.sms.pojo.dto.CategoryDTO;
import com.cs.sms.pojo.entity.Admin;
import com.cs.sms.pojo.entity.Category;
import com.cs.sms.pojo.vo.AdminVO;
import com.cs.sms.web.JsonPage;

import java.util.List;

public interface ICategoryService {
    /**
     * 增加类别
     * @param categoryDTO
     */
    void addNew(CategoryDTO categoryDTO);

    /**
     * 根据ID删除对应数据
     * @param id 类别ID
     */
    void deleteById(Long id);

    /**
     * 根据id修改类别信息
     * @param categoryDTO
     */
    void updateById(CategoryDTO categoryDTO);

    /**
     * 根据id查询到的类别信息
     * @return
     */
    Category selectById(Long id);

    /**
     * 根据类别名查询相关数据
     * @param name 类别姓名
     * @return
     */
    Category selectByName(String name);

    /**
     * 查询所有类别信息
     * @return
     */
    List<Category> list();

    // 分页查询
    JsonPage<Category> getAllCategoryByPage(Integer pageNum, Integer pageSize);

    // 添加商品与类别关系
    void setGoodsCategory(Long goodsId, Long categoryId);
}
