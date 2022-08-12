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
    /**
     * 根据商品名称统计商品数量
     * @param name 商品名称
     * @return 商品数量
     */
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

    /**
     *  根据商品ID修改商品列表数据
     * @param purchase 商品信息
     * @return 受影响的行数，成功修改数据时，将返回1
     */
    int updateById(Purchase purchase);
    /**
     * 根据id修改品牌的名称
     * @param id 品牌id
     * @param name 新的品牌名称
     * @return 受影响的行数，当修改成功时，将返回1，如果无此id对应的数据，将返回0
     */
    int updateNameById(@Param("id") Long id, @Param("name") String name);

    /**
     * 统计商品所有的数据
     * @return
     */
    int count();

    /**
     * 根据ID查询商品数据
     * @param id 商品编号(ID)
     * @return 成功查询到商品后,返回查询到的商品数据
     */
    PurchaseDetailVO getById(Long id);

    /**
     * 根据名称查询商品数据
     * @param name 商品名称(name)
     * @return
     */
    PurchaseDetailVO getByName(String name);
    /**
     *  查看商品列表
     * @return 商品列表
     */
    List<PurchaseListItemVO> list();

    /**
     * 分页查询所有商品
     * @param offset
     * @param count
     * @return
     */
    List<PurchaseListItemVO> listPage(Integer offset, Integer count);
}