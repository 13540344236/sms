package com.cs.sms.repo.impl;


import com.cs.sms.repo.IBrandRepository;
import com.cs.sms.repo.ICategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class CategoryRepositoryImpl implements ICategoryRepository {

    public CategoryRepositoryImpl() {
        log.debug("创建数据访问对象：CategoryRepositoryImpl");
    }

}
