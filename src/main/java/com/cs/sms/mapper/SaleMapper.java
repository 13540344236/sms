package com.cs.sms.mapper;


import com.cs.sms.pojo.entity.Purchase;
import com.cs.sms.pojo.entity.Sale;
import com.cs.sms.pojo.vo.PurchaseListItemVO;
import com.cs.sms.pojo.vo.SaleDetailVO;
import com.cs.sms.pojo.vo.SaleListItemVO;

import java.util.List;

public interface SaleMapper {

    int deleteByPrimaryKey(Long id);

    int countByName(String name);

    int insert(Sale sale);

    SaleDetailVO getById(Long id);


    int updateById(Sale sale);

    List<SaleListItemVO> list();

    List<SaleListItemVO> listPage(Integer pageNum, Integer pageSize);
}