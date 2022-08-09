package com.cs.sms.mapper;

import com.cs.sms.pojo.entity.Purchase;
import com.cs.sms.pojo.vo.PurchaseDetailVO;
import com.cs.sms.pojo.vo.PurchaseListItemVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PurchaseMapper {
    /**
     * 插入商品列表数据
     * @param purchase 商品列表数据
     * @return 受影响行数，成功插入数据时，将返回1
     */
    int insert(Purchase purchase);

    int countByName(String name);

    /**
     *删除角色id
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    int updateById(Purchase purchase);

    int updateNameById(@Param("id") Long id, @Param("name") String name);

    int count();

    PurchaseDetailVO getById(Long id);

    PurchaseDetailVO getByName(String name);

    List<PurchaseListItemVO> list();
    List<PurchaseListItemVO> listPage(Integer offset, Integer count);
}