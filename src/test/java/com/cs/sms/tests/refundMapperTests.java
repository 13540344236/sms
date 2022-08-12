package com.cs.sms.tests;

import com.cs.sms.mapper.RefundMapper;
import com.cs.sms.pojo.entity.Refund;
import com.cs.sms.pojo.vo.RefundDetailVO;
import com.cs.sms.pojo.vo.RefundListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class refundMapperTests {
    @Autowired
    RefundMapper mapper;

    @Test
    public void testInsert(){
        Refund refund =new Refund();
        refund.setName("可口可乐");
        refund.setCategory("饮料");
        refund.setGoodsSpecification("瓶");
        refund.setWarehousingQuantity(20);
        refund.setSupplier("刘先生");
        refund.setOperator("最高管理员");
        log.debug("插入数据之前，参数={}",refund);
        int rows = mapper.insert(refund);
        log.debug("rows = {}", rows);
    }

    @Test
    public void testUpdateById(){
        Long id = 2L;
        String name = "零食大礼包";
        String category = "零食";
        String goodsSpecification = "袋";
        String supplier= "李先生";
        Refund refund = new Refund();
        refund.setId(id);
        refund.setName(name);
        refund.setCategory(category);
        refund.setGoodsSpecification(goodsSpecification);
        refund.setSupplier(supplier);
        int rows = mapper.updateById(refund);
        log.debug("修改商品信息完成，受影响的行数={}",rows);
    }

    @Test
    public void testDeleteById() {
        Long id = 12L;
        int rows = mapper.deleteByPrimaryKey(id);
        log.debug("删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testUpdateNameById() {
        Long id = 3L;
        String name = "脉动";
        int rows = mapper.updateNameById(id, name);
        log.debug("修改商品名称完成，受影响的行数={}", rows);
    }
    @Test
    public void testCount() {
        int count = mapper.count();
        log.debug("统计商品数量完成，统计结果={}", count);
    }
    @Test
    public void testCountByName() {
        String name = "可口可乐";
        int count = mapper.countByName(name);
        log.debug("根据名称【{}】统计商品数量完成，统计结果={}", name, count);
    }
    @Test
    public void testCountById() {
        Long id = 5L;
        int count = mapper.countById(id);
        log.debug("根据名称【{}】统计商品数量完成，统计结果={}", id, count);
    }
    @Test
    public void testGetById() {
        Long id = 4L;
        RefundDetailVO refundDetailVO = mapper.getById(id);
        log.debug("根据id={}查询完成，结果={}", id, refundDetailVO);
    }
    @Test
    public void testGetByName() {
        String name ="可口可乐";
        RefundDetailVO refundDetailVO = mapper.getByName(name);
        log.debug("根据name={}查询完成，结果={}", name, refundDetailVO);
    }
    @Test
    public void testList(){
        List<RefundListItemVO> list = mapper.list();
        log.debug("查询列表完成，结果集中的数据的数量={}",list.size());
        for (RefundListItemVO item : list) {
            log.debug("{}",item);
        }
    }
    @Test
    public void testListPage() {
        Integer offset = 5;
        Integer count = 2;
        List<RefundListItemVO> list = mapper.listPage(offset, count);
        log.debug("查询列表完成，结果集中的数据的数量={}", list.size());
        for (RefundListItemVO item : list) {
            log.debug("{}", item);
        }
    }

}
