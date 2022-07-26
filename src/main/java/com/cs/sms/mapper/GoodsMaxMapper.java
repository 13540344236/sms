package com.cs.sms.mapper;

import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.GoodsBad;
import com.cs.sms.pojo.entity.GoodsMax;
import com.cs.sms.pojo.vo.GoodsBadVO;
import com.cs.sms.pojo.vo.GoodsMaxVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMaxMapper {

    /**
     * 插入商品列表数据
     * @param goodsMax 商品列表数据
     * @return 受影响的行数，成功插入数据时，将返回1
     */
    int insert(GoodsMax goodsMax);

    /**
     * 根据商品ID删除商品列表数据
     * @param id 商品编号(ID)
     * @return 受影响的行数，成功删除数据时，将返回1
     */
    int deleteById(Long id);

    /**
     * 根据商品ID删除商品列表数据
     * @param ids 商品编号(ID)
     * @return 受影响的行数，成功删除数据时，将返回1
     */
    int deleteByIds(Long[] ids);

    /**
     * 根据商品ID修改商品列表数据
     * @param goodsMax 商品信息
     * @return 受影响的行数，成功修改数据时，将返回1
     */
    int updateById(GoodsMax goodsMax);

    /**
     * 根据ID查询商品数据
     * @param id 商品编号(ID)
     * @return 成功查询到商品后,返回查询到的商品数据
     */
    GoodsMaxVO selectById(Long id);

    /**
     * 根据商品名称统计商品数量
     * @param name 商品名称
     * @return 商品数量
     */
    int countByName(String name);

    /**
     *  查看商品列表
     * @return 商品列表
     */
    List<GoodsMaxVO> list();

    /**
     * 分页查询所有商品报溢
     * @return 商品报溢列表
     */
    List<GoodsMax> findAllGoodsMax();
}