package com.cs.sms.service.impl;



import com.cs.sms.repo.IPictureRepository;
import com.cs.sms.repo.IPurchaseRepository;
import com.cs.sms.service.IPurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class PurchaseServiceImpl implements IPurchaseService {
    @Autowired
    private IPurchaseRepository purchaseRepository;
    public PurchaseServiceImpl() {
        log.debug("创建业务逻辑对象：PurchaseServiceImpl");
    }

}
