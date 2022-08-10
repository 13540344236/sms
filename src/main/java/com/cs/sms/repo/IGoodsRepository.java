package com.cs.sms.repo;

import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.pojo.vo.SupplierListVO;

import java.util.List;

public interface IGoodsRepository {

    String KEY_PREFIX_SUPPLIER_ITEM = "good:item:";
    String KEY_SUPPLIER_LIST = "good:list";

    void put(GoodsListVO goodsListVO);

    GoodsListVO get(Long id);

    void deleteItem(Long id);

    void putList(List<GoodsListVO> list);

    List<GoodsListVO> getList();

    void deleteList();
}
