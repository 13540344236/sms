package com.cs.sms.mapper;

import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.Menu;
import com.cs.sms.pojo.vo.GoodsDetailVO;
import com.cs.sms.pojo.vo.GoodsListVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {

    int insert(Menu menu);

    int deleteById(Integer id);

    int updateById(Menu menu);

    Menu selectById(Integer id);

    List<Menu> selectByName(String name);

    int countByName(String name);

    List<Menu> list();

    List<Menu> findAllMenu();

    Menu getById(Integer menuId);
}
