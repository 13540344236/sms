package com.cs.sms.tests;

import com.cs.sms.mapper.MemberMapper;
import com.cs.sms.pojo.entity.Member;
import com.cs.sms.pojo.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class MemberMapperTests {

    @Autowired
    MemberMapper mapper;

    @Test
    public void insertTest(){
        for(int i = 0; i<10; i++){
            String name="陈哈7"+i;
            String phone="1234567"+i;
            Member member=new Member();
            member.setName(name);
            member.setPhone(phone);
            int rows = mapper.insert(member);
            log.debug("插入成功返回受影响行数:{}",rows);
        }
    }

    @Test
    public void deleteTests(){
        Long id=2L;
        int rows= mapper.deleteById(id);
        log.debug("返回受影响行数:{}",rows);
    }
    @Test
    public void updateTests(){
        Long id=3L;
        String phone="1234562";
        String name="周星星";
        Member member=new Member();
        member.setId(id);
        member.setPhone(phone);
        member.setName(name);
        int i = mapper.updateById(member);
        log.debug("受影响行数:{}",i);
    }

    @Test
    public void selectTests(){
        String phone="1234562";
        MemberVO memberVO = mapper.selectByPhone(phone);
        log.debug("返回查询到的数据:{}",memberVO);
    }

    @Test
    public void selectListTests(){
        List<MemberVO> list = mapper.list();
        for (MemberVO m:list
             ) {
            log.debug("返回所有会员数据:{}",m);
        }
    }
    @Test
    public void selectByIdTests(){
        Long id=8L;
        MemberVO memberVO = mapper.selectById(id);
        log.debug("查询到的数据:{}",memberVO);
    }

}
