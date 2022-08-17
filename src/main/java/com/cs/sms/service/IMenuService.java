package com.cs.sms.service;


import com.cs.sms.pojo.dto.GoodsAddNewDTO;
import com.cs.sms.pojo.dto.GoodsEditDTO;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.Menu;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.web.JsonPage;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IMenuService {
    // 新增商品
    void addNew(Menu menu);

    // 根据商品ID删除商品
    void deleteById(Integer id);

    // 修改商品信息
    void update(Integer id, Menu menu);

    // 查询商品列表
    List<Menu> list();

    //根据名称模糊查询
    List<Menu> selectByName(String name);

    // 分页查询
    JsonPage<Menu> getAllMenuByPage(Integer pageNum, Integer pageSize);

    Menu getById(Integer menuId);
}
