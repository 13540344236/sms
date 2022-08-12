package com.cs.sms.tests;

import com.cs.sms.mapper.RoleMapper;
import com.cs.sms.pojo.dto.RoleDTO;
import com.cs.sms.pojo.entity.Role;
import com.cs.sms.pojo.vo.RoleVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class RoleMapperTests {
    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void Role(){

        Role role = new Role();
        role.setName("yyy");
        roleMapper.insert(role);
    }

    @Test
    public void Roles(){
        int yyy = roleMapper.select("yy");
        System.out.println(yyy);
    }

    @Test
    public void R(){
        List<RoleVO> byId = roleMapper.getById(12l);
        System.out.println(byId);
    }


    @Test
    public void dd()
    {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(
                dateTime
        );}
}
