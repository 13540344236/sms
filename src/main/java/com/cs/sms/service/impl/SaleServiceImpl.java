package com.cs.sms.service.impl;



import com.cs.sms.repo.IPurchaseRepository;
import com.cs.sms.repo.ISaleRepository;
import com.cs.sms.service.IPurchaseService;
import com.cs.sms.service.ISaleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SaleServiceImpl implements ISaleService {
    @Autowired
    private ISaleRepository saleRepository;
    public SaleServiceImpl() {
        log.debug("创建业务逻辑对象：SaleServiceImpl");
    }

}
