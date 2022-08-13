package com.cs.sms.service;


import com.cs.sms.pojo.dto.GoodsBadDTO;
import com.cs.sms.pojo.dto.GoodsMaxDTO;
import com.cs.sms.pojo.entity.GoodsMax;
import com.cs.sms.pojo.vo.GoodsBadVO;
import com.cs.sms.pojo.vo.GoodsMaxVO;
import com.cs.sms.web.JsonPage;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IGoodsMaxService {
    // 新增商品
    void addNew(GoodsMaxDTO goodsMaxDTO);

    // 根据商品ID删除商品
    void deleteById(Long id);

    // 修改商品信息
    void update(Long id, GoodsMaxVO goodsMaxVO);

    // 查询商品列表
    List<GoodsMaxVO> list();

    //导出商品报表
    void createExcel(HttpServletResponse response) throws IOException;

    // 分页查询
    JsonPage<GoodsMax> getAllGoodsByPage(Integer pageNum, Integer pageSize);


}
