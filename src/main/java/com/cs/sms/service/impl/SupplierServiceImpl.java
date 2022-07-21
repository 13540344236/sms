package com.cs.sms.service.impl;



import com.cs.sms.repo.ISaleRepository;
import com.cs.sms.repo.ISupplierRepository;
import com.cs.sms.service.ISupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SupplierServiceImpl implements ISupplierService {
    @Autowired
    private ISupplierRepository supplierRepository;
    public SupplierServiceImpl() {
        log.debug("创建业务逻辑对象：SupplierServiceImpl");
    }

}
