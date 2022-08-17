package com.cs.sms.tests;

import com.cs.sms.mapper.CategoryMapper;
import com.cs.sms.pojo.entity.Admin;
import com.cs.sms.pojo.entity.Category;
import com.cs.sms.pojo.vo.AdminVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.List;

@SpringBootTest
@Slf4j
public class CategoryTests {

    @Autowired
    CategoryMapper categoryMapper;

    @Test
    public void insertTests(){
        for(int i=1;i<15;i++){
            String name="水果"+i;
            Byte parentId=1;
            Category category=new Category();
            category.setName(name);
            category.setIsParent(parentId);
            int insert = categoryMapper.insert(category);
            log.debug("受影响返回行数:{}",insert);
        }
    }

    @Test
    public void deleteTests(){
        int i = categoryMapper.deleteById(1L);
        log.debug("受影响返回行数:{}",i);
    }

    @Test
    public void updateByIdTests(){
        Long id=7L;
        String name="陈鑫";
        Category category=new Category();
        category.setId(id);
        category.setName(name);
        int rows = categoryMapper.updateById(category);
        log.debug("受影响行数:{}",rows);

    }

    @Test
    public void selectByIdTests(){
        Long id=12L;
        Category category = categoryMapper.selectById(id);
        log.debug("查到的数据:{}",category);

    }

    @Test
    public void selectByNameTests(){
        String name="水果11";
        Category category = categoryMapper.selectByName(name);
        log.debug("查询到的数据为:{}",category);
    }

    @Test
    public void listTests(){
        List<Category> list = categoryMapper.list();
        for (Category a:list
        ) {
            log.debug("查询到所有员工信息:{}",a);
        }
    }

}
