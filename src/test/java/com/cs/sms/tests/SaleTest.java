package com.cs.sms.tests;

import com.cs.sms.mapper.SaleMapper;
import com.cs.sms.pojo.entity.Refund;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@Slf4j
@SpringBootTest
public class SaleTest {
    @Autowired
    SaleMapper saleMapper;
    @Test
    public void testInsert(){
        List<HashMap<String, Object>> list = saleMapper.getGoodsSale();
        log.debug("销售数据为:"+list);
    }
}
