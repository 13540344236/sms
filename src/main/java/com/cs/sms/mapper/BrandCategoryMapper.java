package com.cs.sms.mapper;

import com.cs.sms.pojo.entity.BrandCategory;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BrandCategoryMapper {
    long countByExample(BrandCategoryExample example);

    int deleteByExample(BrandCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BrandCategory record);

    int insertSelective(BrandCategory record);

    List<BrandCategory> selectByExample(BrandCategoryExample example);

    BrandCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BrandCategory record, @Param("example") BrandCategoryExample example);

    int updateByExample(@Param("record") BrandCategory record, @Param("example") BrandCategoryExample example);

    int updateByPrimaryKeySelective(BrandCategory record);

    int updateByPrimaryKey(BrandCategory record);
}