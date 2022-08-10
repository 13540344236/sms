package com.cs.sms.service;


import com.cs.sms.pojo.dto.PurchaseAddNewDTO;
import com.cs.sms.pojo.dto.PurchaseEditDTO;
import com.cs.sms.pojo.vo.PurchaseListItemVO;

import java.util.List;

public interface IPurchaseService {
    /**
     * 添加商品
     * @param purchaseAddNewDTO
     */

    void addNew(PurchaseAddNewDTO purchaseAddNewDTO);

    /**
     * 根据id删除商品数据
     * @param id
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 查询商品列表
     * @return
     */

    List<PurchaseListItemVO> list();

    void update(Long id, PurchaseEditDTO purchaseEditDTO);
}
