package com.cs.sms.repo.impl;








import com.cs.sms.repo.ISupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class SupplierRepositoryImpl implements ISupplierRepository {

    public SupplierRepositoryImpl() {
        log.debug("创建数据访问对象：SupplierRepositoryImpl");
    }

}
