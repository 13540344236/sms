package com.cs.sms.mapper;

import com.cs.sms.pojo.entity.BrandCategory;

public interface BrandCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BrandCategory record);

    int insertSelective(BrandCategory record);

    BrandCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BrandCategory record);

    int updateByPrimaryKey(BrandCategory record);
}