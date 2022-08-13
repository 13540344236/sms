package com.cs.sms.mapper;

import com.cs.sms.pojo.dto.GoodsBadDTO;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.GoodsBad;
import com.cs.sms.pojo.vo.GoodsBadVO;
import com.cs.sms.pojo.vo.GoodsDetailVO;
import com.cs.sms.pojo.vo.GoodsListVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsBadMapper {

    /**
     * 插入商品列表数据
     * @param goodsBad 商品列表数据
     * @return 受影响的行数，成功插入数据时，将返回1
     */
    int insert(GoodsBad goodsBad);

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
     * @param goodsBad 商品信息
     * @return 受影响的行数，成功修改数据时，将返回1
     */
    int updateById(GoodsBad goodsBad);

    /**
     * 根据ID查询商品数据
     * @param id 商品编号(ID)
     * @return 成功查询到商品后,返回查询到的商品数据
     */
    GoodsBadVO selectById(Long id);

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
    List<GoodsBadVO> list();

    /**
     * 分页查询所有商品
     * @return 商品列表
     */
    List<Goods> findAllGoods();
}