package com.cs.sms.service.impl;



import com.cs.sms.repo.IBrandRepository;
import com.cs.sms.service.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class BrandServiceImpl implements IBrandService {
    @Autowired
    private IBrandRepository brandRepository;

    public BrandServiceImpl() {
        log.debug("创建业务逻辑对象：BrandServiceImpl");
    }

}
