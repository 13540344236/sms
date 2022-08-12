package com.cs.sms.repo.impl;

import com.cs.sms.pojo.vo.RefundListItemVO;
import com.cs.sms.repo.IRefundRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Repository
public class RefundRepositoryImpl  {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
    public RefundRepositoryImpl(){
        log.debug("创建数据访问对象：PurchaseRepositoryImpl");
    }

}
