package com.cs.sms.tests;

import com.cs.sms.mapper.SupplierMapper;
import com.cs.sms.pojo.entity.Supplier;
import com.cs.sms.service.ISupplierService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SupplierTest {
    @Autowired
    private ISupplierService service;

    @Autowired
    private SupplierMapper mapper;
    @Test
    public void test(){
        Supplier supplier =new Supplier();
        supplier.setSupplier("航天公司");
        mapper.insert(supplier);
    }
}
