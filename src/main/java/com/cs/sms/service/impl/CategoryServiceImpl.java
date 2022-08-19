package com.cs.sms.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.cs.sms.ex.ServiceException;
import com.cs.sms.mapper.AdminMapper;
import com.cs.sms.mapper.CategoryMapper;
import com.cs.sms.mapper.GoodsCategoryMapper;
import com.cs.sms.pojo.dto.AdminDTO;
import com.cs.sms.pojo.dto.CategoryDTO;
import com.cs.sms.pojo.entity.Admin;
import com.cs.sms.pojo.entity.Category;
import com.cs.sms.pojo.vo.AdminVO;
import com.cs.sms.repo.IBrandRepository;

import com.cs.sms.repo.ICategoryRepository;
import com.cs.sms.service.ICategoryService;
import com.cs.sms.web.JsonPage;
import com.cs.sms.web.Results;
import com.cs.sms.web.ServiceCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryMapper mapper;
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;


    @Transactional
    @Override
    public void addNew(CategoryDTO categoryDTO) {
        log.debug("尝试插入数据:{}",categoryDTO);
        String name=categoryDTO.getName();
        Category category= mapper.selectByName(name);
        if(category != null){
            String message="该类别已存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }
        Category category1=new Category();
        BeanUtils.copyProperties(categoryDTO,category1);
        category1.setIsParent((byte)1);
        category1.setEnable((byte)1);
        category1.setIsDisplay((byte)1);
        //设置管理员的创建时间为当前系统时间
        category1.setGmtCreate(new Date());
        log.debug("尝试插入数据:{}",category1);
        int rows = mapper.insert(category1);
        if(rows!=1){
            String message="增加员工失败,服务器忙,请稍后在试";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.debug("需要输出的类别id:{}",id);
        Category category=mapper.selectById(id);
        if(category == null){
            String message="尝试删除的数据不存在,请稍后在试";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
        int rows = mapper.deleteById(id);
        if(rows != 1){
            String message="删除类别失败,服务器忙,请稍后在试";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
    }

    @Override
    public void updateById(CategoryDTO categoryDTO) {
        log.debug("需要修改的员工信息:{}",categoryDTO);
        Category category=new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        int rows = mapper.updateById(category);
        if(rows!=1){
            String message="尝试修改数据失败,服务器忙请稍后尝试";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE,message);
        }
    }

    @Override
    public Category selectById(Long id) {
        log.debug("需要查询的类别信息的id:{}",id);
        Category category = mapper.selectById(id);
        if(category != null){
            return category;
        }else {
            String message="该员工不存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
    }
    @Override
    public Category selectByName(String name) {
        log.debug("传入的员工姓名:{}",name);
        Category category = mapper.selectByName(name);
        if(category != null){
            return category;
        }else {
            String message="该员工不存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
    }

    @Override
    public List<Category> list() {
        return mapper.list();
    }

    //分页查询商品列表
    @Override
    public JsonPage<Category> getAllCategoryByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        log.debug("num = {},Size = {}",pageNum,pageSize);
        List<Category> list = mapper.findAllCategory();
        return JsonPage.restPage(new PageInfo<>(list));
    }

    //
    @Override
    public void setGoodsCategory(Long goodsId, Long categoryId) {
        goodsCategoryMapper.insert(goodsId,categoryId);
    }

}
