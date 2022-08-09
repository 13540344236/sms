package com.cs.sms.repo;

import com.cs.sms.pojo.vo.SupplierListVO;

import java.util.List;

public interface ISupplierRepository {

    String KEY_PREFIX_SUPPLIER_ITEM = "supplier:item:";
    String KEY_SUPPLIER_LIST = "supplier:list";

    void put(SupplierListVO supplierListVO);

    SupplierListVO get(Long id);

    void deleteItem(Long id);

    void putList(List<SupplierListVO> list);

    List<SupplierListVO> getList();

    void deleteList();
}
