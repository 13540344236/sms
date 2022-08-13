package com.cs.sms.mapper;

import com.cs.sms.pojo.entity.Supplier;
import com.cs.sms.pojo.vo.SupplierListVO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SupplierMapper {
    int deleteByPrimaryKey(Long... ids);

    int insert(Supplier record);

    List<SupplierListVO> list();

    int updateByPrimaryKeySelective(Supplier supplier);


    int countByName(String supplier);

    SupplierListVO getById(Long id);
}