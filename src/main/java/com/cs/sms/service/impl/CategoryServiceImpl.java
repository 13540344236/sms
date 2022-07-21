package com.cs.sms.service.impl;



import com.cs.sms.repo.IBrandRepository;

import com.cs.sms.repo.ICategoryRepository;
import com.cs.sms.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    public CategoryServiceImpl() {
        log.debug("创建业务逻辑对象：CategoryServiceImpl");
    }

}
