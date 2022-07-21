package com.cs.sms.service.impl;



import com.cs.sms.repo.ICategoryRepository;
import com.cs.sms.repo.IGoodsBrandRepository;
import com.cs.sms.service.ICategoryService;
import com.cs.sms.service.IGoodsBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class GoodsBrandServiceImpl implements IGoodsBrandService {
    @Autowired
    private IGoodsBrandRepository goodsBrandRepository;

    public GoodsBrandServiceImpl() {
        log.debug("创建业务逻辑对象：GoodsBrandServiceImpl");
    }

}
