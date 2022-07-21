package com.cs.sms.mapper;

import com.cs.sms.pojo.entity.Purchase;

public interface PurchaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Purchase record);

    int insertSelective(Purchase record);

    Purchase selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Purchase record);

    int updateByPrimaryKey(Purchase record);
}