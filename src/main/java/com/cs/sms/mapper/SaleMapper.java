package com.cs.sms.mapper;

import com.cs.sms.pojo.entity.Sale;

public interface SaleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Sale record);

    int insertSelective(Sale record);

    Sale selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sale record);

    int updateByPrimaryKey(Sale record);
}