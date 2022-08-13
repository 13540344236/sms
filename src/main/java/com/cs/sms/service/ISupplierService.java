package com.cs.sms.service;


import com.cs.sms.pojo.dto.SupplierAddNewDTO;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.Supplier;
import com.cs.sms.pojo.vo.SupplierListVO;
import com.cs.sms.web.JsonPage;

import java.util.List;

public interface ISupplierService {
    void addNew(SupplierAddNewDTO supplierAddNewDTO);

    void delete(Long id);

    List<SupplierListVO> list();

    void update(Supplier supplier);

    // 分页查询
    JsonPage<Supplier> getAllGoodsByPage(Integer pageNum, Integer pageSize);

}
