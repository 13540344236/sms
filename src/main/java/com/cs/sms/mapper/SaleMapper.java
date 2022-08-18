package com.cs.sms.mapper;
import com.cs.sms.pojo.entity.Sale;
import com.cs.sms.pojo.vo.SaleDetailVO;
import com.cs.sms.pojo.vo.SaleListItemVO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
@Repository
public interface SaleMapper {
    /**
     * 删除角色id
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  根据商品名称统计商品数量
     * @param name 商品名称
     * @return 商品数量
     */
    int countByName(String name);

    /**
     * 插入商品列表数据
     * @param sale 商品列表数据
     * @return 受影响行数，成功插入数据时，将返回1
     */
    int insert(Sale sale);

    /**
     * 根据ID查询商品数据
     * @param id  商品编号(ID)
     * @return 成功查询到商品后,返回查询到的商品数据
     */

    SaleDetailVO getById(Long id);

    /**
     * 根据商品ID修改商品列表数据
     * @param sale 商品信息
     * @return 受影响的行数，成功修改数据时，将返回1
     */

    int updateById(Sale sale);

    /**
     * 查看商品列表
     * @return 商品列表
     */
    List<SaleListItemVO> list();

    /**
     * 分页查询所有销售信息
     * @return 销售信息列表
     */
    List<Sale> findAllSale();

    /**
     * 查询销量前3名
     * @return
     */
    List<HashMap<String, Object>> getGoodsSale();
}