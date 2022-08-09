package com.cs.sms.tests;


import com.cs.sms.mapper.AdminMapper;
import com.cs.sms.pojo.entity.Admin;
import com.cs.sms.pojo.vo.AdminVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class AdminMapperTests {

    @Autowired
    AdminMapper adminMapper;

    @Test
    public void insertTests(){
        for(int i = 0; i<10; i++){
            String name="管理员测试"+i;
            String gander="男";
            Admin admin=new Admin();
            admin.setStaffName(name);
            admin.setGender(gander);
            int rows = adminMapper.insert(admin);
            log.debug("插入成功返回受影响行数:{}",rows);
        }
    }
    @Test
    public void deleteByIdTests(){
        Long id=3L;
        int rows = adminMapper.deleteById(id);
        log.debug("删除成功受影响行数:{}",rows);
    }
    @Test
    public void updateByIdTests(){
        Long id=7L;
        String name="陈鑫";
        Admin admin=new Admin();
        admin.setId(id);
        admin.setStaffName(name);
        int rows = adminMapper.updateById(admin);
        log.debug("受影响行数:{}",rows);

    }

    @Test
    public void selectByIdTests(){
        Long id=12L;
        AdminVO adminVO = adminMapper.selectById(id);
        log.debug("查到的数据:{}",adminVO);

    }

    @Test
    public void selectByNameTests(){
        String name="陈哈3";
        AdminVO adminVOS = adminMapper.selectByName(name);
        log.debug("查询到的数据为:{}",adminVOS);
    }

    @Test
    public void listTests(){
        List<AdminVO> list = adminMapper.list();
        for (AdminVO a:list
             ) {
            log.debug("查询到所有员工信息:{}",a);
        }
    }
}
