package com.cs.sms.service.impl;



import com.cs.sms.repo.IGoodsBrandRepository;
import com.cs.sms.repo.IGoodsRepository;
import com.cs.sms.service.IGoodsBrandService;
import com.cs.sms.service.IGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private IGoodsRepository goodsRepository;

    public GoodsServiceImpl() {
        log.debug("创建业务逻辑对象：GoodsServiceImpl");
    }

}
