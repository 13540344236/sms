package com.cs.sms.repo.impl;


import com.cs.sms.repo.IBrandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class BrandRepositoryImpl implements IBrandRepository {

    public BrandRepositoryImpl() {
        System.out.println("创建数据访问对象：BrandRepositoryImpl");
    }

}
