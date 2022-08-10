package com.cs.sms.tests;

import com.cs.sms.mapper.SupplierMapper;
import com.cs.sms.pojo.entity.Supplier;
import com.cs.sms.service.ISupplierService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class SupplierTest {
    @Autowired
    private ISupplierService service;

    @Autowired
    private SupplierMapper mapper;
    @Test
    public void test(){
        for (int i = 0; i < 10; i++) {
            Supplier supplier =new Supplier();
            supplier.setSupplier("可达冰淇淋"+i);
            supplier.setGmtCreate(new Date());
            mapper.insert(supplier);
        }
    }
}
