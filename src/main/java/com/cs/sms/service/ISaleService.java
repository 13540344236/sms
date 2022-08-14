package com.cs.sms.service;

import com.cs.sms.pojo.dto.SaleAddNewDTO;
import com.cs.sms.pojo.dto.SaleEditDTO;
import com.cs.sms.pojo.entity.Sale;
import com.cs.sms.pojo.vo.SaleListItemVO;
import com.cs.sms.web.JsonPage;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public interface ISaleService {
    /**
     * 添加商品
     * @param saleAddNewDTO
     */

    void addNew(SaleAddNewDTO saleAddNewDTO);
    /**
     * 查询商品列表
     * @return
     */

 List<SaleListItemVO>list();

    /**
     * 修改商品信息
     * @param id
     * @param saleEditDTO
     */
    void update(Long id, SaleEditDTO saleEditDTO);

    /**
     * 导出商品表格
     * @param response
     * @throws IOException
     */
    void createExcel(HttpServletResponse response)throws IOException;

    // 返回JsonPage类型分页查询订单的方法
    JsonPage<Sale> getAllSaleByPage(Integer pageNum, Integer pageSize);

    void deleteByPrimaryKey(Long id);
}

