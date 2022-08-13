package com.cs.sms.service;


import com.cs.sms.pojo.dto.GoodsAddNewDTO;
import com.cs.sms.pojo.dto.GoodsBadDTO;
import com.cs.sms.pojo.dto.GoodsEditDTO;
import com.cs.sms.pojo.entity.GoodsBad;
import com.cs.sms.pojo.vo.GoodsBadVO;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.web.JsonPage;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IGoodsBadService {
    // 新增商品
    void addNew(GoodsBadDTO goodsBadDTO);

    // 根据商品ID删除商品
    void deleteById(Long id);

    // 修改商品信息
    void update(Long id, GoodsBadVO goodsbadVO);

    // 查询商品列表
    List<GoodsBadVO> list();

    //导出商品报表
    void createExcel(HttpServletResponse response) throws IOException;

    // 分页查询
    JsonPage<GoodsBad> getAllGoodsByPage(Integer pageNum, Integer pageSize);


}
