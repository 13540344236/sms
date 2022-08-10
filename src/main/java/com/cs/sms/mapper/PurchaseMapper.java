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
    /**
     * 根据若干个品牌id，批量删除品牌数据
     * @param ids 期望删除的若干个品牌数据的id
     * @return 受影响的行数，将返回成功删除的数据量
     */
    int deleteByIds(Long... ids);

    int updateById(Purchase purchase);
    /**
     * 根据id修改品牌的名称
     * @param id 品牌id
     * @param name 新的品牌名称
     * @return 受影响的行数，当修改成功时，将返回1，如果无此id对应的数据，将返回0
     */
    int updateNameById(@Param("id") Long id, @Param("name") String name);

    int count();

    PurchaseDetailVO getById(Long id);

    PurchaseDetailVO getByName(String name);

    List<PurchaseListItemVO> list();
    List<PurchaseListItemVO> listPage(Integer offset, Integer count);
}