package com.cs.sms.service;


import com.cs.sms.pojo.dto.GoodsAddNewDTO;
import com.cs.sms.pojo.dto.GoodsEditDTO;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.web.JsonPage;

import java.util.List;

public interface IGoodsService {
    // 新增商品
    void addNew(GoodsAddNewDTO goodsAddNewDTO);

    // 根据商品ID删除商品
    void deleteById(Long id);

    // 修改商品信息
    void update(Long id, GoodsEditDTO goodsEditVO);

    // 查询商品列表
    List<GoodsListVO> list();

//    // 分页查询
//    JsonPage<Goods> getAllGoodsByPage(Integer pageNum, Integer pageSize);


}
