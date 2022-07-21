package com.cs.sms.service.impl;


import com.cs.sms.repo.IAlbumRepository;
import com.cs.sms.repo.IBrandCategoryRepository;
import com.cs.sms.service.IAlbumService;
import com.cs.sms.service.IBrandCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class BrandCategoryServiceImpl implements IBrandCategoryService {
    @Autowired
    private IBrandCategoryRepository brandCategoryRepository;

    public BrandCategoryServiceImpl() {
        log.debug("创建业务逻辑对象：BrandCategoryServiceImpl");
    }

}
