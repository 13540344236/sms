package com.cs.sms.repo.impl;



import com.cs.sms.repo.IGoodsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class GoodsRepositoryImpl implements IGoodsRepository {

    public GoodsRepositoryImpl() {
        log.debug("创建数据访问对象：GoodsRepositoryImpl");
    }

}
