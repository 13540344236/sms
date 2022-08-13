package com.cs.sms.tests;

import com.cs.sms.mapper.GoodsMapper;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.vo.GoodsListVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class GoodsMapperTests {
    @Autowired
    GoodsMapper goodsMapper;

    @Test
    public void testInsert() {
        for (int i = 11; i < 21; i++) {
            Goods goods = new Goods();
            goods.setName("家具"+i);
            goods.setUrl("baidu");
            goods.setCategory("家具");
            goods.setCurrentStock(1L);
            goods.setLowLimitStock(99L);
            int rows = goodsMapper.insert(goods);
        }
    }

    @Test
    public void testCountByName() {
        String name = "薯片";
        int rows = goodsMapper.countByName(name);
        log.debug("rows = {}",rows);
    }

    @Test
    public void testUpdate() {
        Long id = 3L;
        Goods goods = new Goods();
        goods.setId(id);
        goods.setName("蓝莓");
        goods.setUrl("baidu");
        goods.setCategory("水果");
        int rows = goodsMapper.updateById(goods);
        log.debug("rows = {}",rows);
    }

    @Test
    public void testGetByPage() {
        List<Goods> allGoods = goodsMapper.findAllGoods();
    }

    @Test
    public void testsByName(){
        String name="水果";
        List<GoodsListVO> goodsListVOS = goodsMapper.selectByName(name);
        for (GoodsListVO g:goodsListVOS
             ) {
            log.debug("查到的数据:{}",g);
        }
    }

    @Test
    public void testByDelete(){
        for (int i = 0; i < 400; i++) {
            Long id=i+1L;
            goodsMapper.deleteById(id);
        }
    }
}
