package com.cs.sms.mapper;

import com.cs.sms.pojo.entity.GoodsBrand;

public interface GoodsBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsBrand record);

    int insertSelective(GoodsBrand record);

    GoodsBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsBrand record);

    int updateByPrimaryKey(GoodsBrand record);
}