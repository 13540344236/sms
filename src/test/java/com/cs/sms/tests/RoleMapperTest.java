package com.cs.sms.tests;

import com.cs.sms.mapper.RoleMenuMapper;
import com.cs.sms.pojo.entity.RoleMenu;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class RoleMapperTest {

    @Autowired
    RoleMenuMapper roleMenuMapper;

//    @Test
//    public void testSelectByRoleId() {
//        Integer roleId = 2;
//        List<RoleMenu> integers = roleMenuMapper.selectByRoleId(roleId);
//        for (Integer integer : integers){
//            log.debug("menuId:{}",integer);
//        }
//    }
}
