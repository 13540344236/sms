package com.cs.sms.tests;

import com.cs.sms.mapper.PurchaseMapper;
import com.cs.sms.pojo.entity.Purchase;
import com.cs.sms.pojo.vo.PurchaseDetailVO;
import com.cs.sms.pojo.vo.PurchaseListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class PurchaseMapperTests {
    @Autowired
    PurchaseMapper purchaseMapper;

    @Test
    public void testInsert(){
        Purchase purchase = new Purchase();
        for (int i = 10; i < 13; i++) {
            purchase.setName("可口可乐"+i);
            purchase.setGoodsCategory("饮料");
            purchase.setGoodsSpecification("瓶");
            purchase.setWarehousingQuantity(20);
            purchase.setSupplier("刘先生");
            purchase.setOperator("最高管理员");
            int rows = purchaseMapper.insert(purchase);
            log.debug("rows = {}", rows);
        }
    }
    @Test
    public void testDeleteById() {
        Long id = 10L;
        int rows = purchaseMapper.deleteByPrimaryKey(id);
        log.debug("删除完成，受影响的行数={}", rows);
    }
    @Test
    public void testUpdateById(){
        Long id = 5L;
        String name = "零食大礼包";
        String goodsCategory = "零食";
        String goodsSpecification = "袋";
        String supplier= "周先生";
        Purchase purchase = new Purchase();
        purchase.setId(id);
        purchase.setName(name);
        purchase.setGoodsCategory(goodsCategory);
        purchase.setGoodsSpecification(goodsSpecification);
        purchase.setSupplier(supplier);
        int rows = purchaseMapper.updateById(purchase);
        log.debug("修改商品信息完成，受影响的行数={}",rows);
    }
    @Test
    public void testUpdateNameById() {
        Long id = 7L;
        String name = "脉动";
        int rows = purchaseMapper.updateNameById(id, name);
        log.debug("修改商品名称完成，受影响的行数={}", rows);
    }
    @Test
    public void testCount() {
        int count = purchaseMapper.count();
        log.debug("统计商品数量完成，统计结果={}", count);
    }
    @Test
    public void testCountByName() {
        String name = "可口可乐";
        int count = purchaseMapper.countByName(name);
        log.debug("根据名称【{}】统计商品数量完成，统计结果={}", name, count);
    }
    @Test
    public void testGetById() {
        Long id = 8L;
        PurchaseDetailVO purchaseDetailVO = purchaseMapper.getById(id);
        log.debug("根据id={}查询完成，结果={}", id, purchaseDetailVO);
    }
    @Test
    public void testGetByName() {
        String name ="可口可乐";
        PurchaseDetailVO purchaseDetailVO = purchaseMapper.getByName(name);
        log.debug("根据name={}查询完成，结果={}", name, purchaseDetailVO);
    }
    @Test
    public void testList(){
        List<PurchaseListItemVO> list = purchaseMapper.list();
        log.debug("查询列表完成，结果集中的数据的数量={}",list.size());
        for (PurchaseListItemVO item : list) {
            log.debug("{}",item);
        }
    }
    @Test
    public void testListPage() {
        Integer offset = 5;
        Integer count = 2;
        List<PurchaseListItemVO> list = purchaseMapper.listPage(offset, count);
        log.debug("查询列表完成，结果集中的数据的数量={}", list.size());
        for (PurchaseListItemVO item : list) {
            log.debug("{}", item);
        }
    }
}
