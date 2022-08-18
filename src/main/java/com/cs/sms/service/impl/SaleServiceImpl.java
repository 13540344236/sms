package com.cs.sms.service.impl;

import com.alibaba.excel.EasyExcel;
import com.cs.sms.ex.ServiceException;
import com.cs.sms.mapper.SaleMapper;

import com.cs.sms.pojo.dto.SaleAddNewDTO;
import com.cs.sms.pojo.dto.SaleEditDTO;

import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.Sale;

import com.cs.sms.pojo.vo.PurchaseDetailVO;
import com.cs.sms.pojo.vo.PurchaseListItemVO;
import com.cs.sms.pojo.vo.SaleDetailVO;
import com.cs.sms.pojo.vo.SaleListItemVO;
import com.cs.sms.repo.ISaleRepository;
import com.cs.sms.service.ISaleService;
import com.cs.sms.web.JsonPage;
import com.cs.sms.web.ServiceCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Slf4j
@Service
public class SaleServiceImpl implements ISaleService {
    @Autowired
    private ISaleRepository saleRepository;
    @Autowired
    private SaleMapper saleMapper;
    public SaleServiceImpl() {
        log.debug("创建业务逻辑对象：SaleServiceImpl");
    }
    //添加商品
    @Override
    public void addNew(SaleAddNewDTO saleAddNewDTO) {
        log.debug("开始处理添加商品的业务，参数：{}",saleAddNewDTO);

        String name = saleAddNewDTO.getName();
        int count = saleMapper.countByName(name);

        // 创建实体对象（Mapper的方法的参数是实体类型）
        Sale sale = new Sale();

        // 将当前方法参数的值复制到purchase实体类型的对象中
        BeanUtils.copyProperties(saleAddNewDTO, sale);
        // 将品牌数据写入到数据库中
        sale.setGmtCreate(new Date());
        log.debug("即将向表中写入数据：{}", sale);
        int rows = saleMapper.insert(sale);
        if (rows != 1) {
            String message = "添加商品失败，服务器忙，请稍后再次尝试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        log.debug("开始处理删除商品的业务，id={}", id);
        // 根据id查询数据
       SaleDetailVO queryResult = saleMapper.getById(id);
        // 判断查询结果是否为null
        if (queryResult == null) {
            // 抛出异常
            String message = "删除商品失败，尝试删除的数据（id=" + id + "）不存在！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 调用mapper对象执行删除，并获取返回值
        int rows = saleMapper.deleteByPrimaryKey(id);
        // 判断返回值是否不为1
        if (rows != 1) {
            // 抛出异常
            String message = "删除商品失败，服务器忙，请稍后再次尝试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
    }

    @Override
    public List<HashMap<String, Object>> getGoodsSale() {
        return saleMapper.getGoodsSale();
    }

    //编辑商品
    @Override
    public void update(Long id, SaleEditDTO saleEditDTO) {
        //根据id查询数据
        SaleDetailVO saleDetailVO = saleMapper.getById(id);
        log.debug("查询到id={}的商品信息为:{}", id, saleDetailVO);
        //判断查询结果是否为null
        if (saleDetailVO == null) {
            //抛出异常
            String message = "编辑商品失败，尝试编辑的数据【id=" + id + "】的数据不存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        Sale sale = new Sale();
        //将当前方法参数的值复制到Purchases实体类型的对象中
        BeanUtils.copyProperties(saleEditDTO, sale);
        //将品牌数据写入到数据库中
        log.debug("即将向表中写入数据:{}", saleEditDTO);
        log.debug("接收到的参数id:{}", id);
        //调用mapper对象执行编辑，并获取返回值
        int rows = saleMapper.updateById(sale);
        log.debug("rows = {}", rows);
        //判断返回值是否为1
        if (rows != 1) {
            String message = "编辑商品失败，服务器忙，请稍后再尝试!";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
    }

    //导出商品报表
    @Override
    public void createExcel(HttpServletResponse response) throws IOException {
        List<SaleListItemVO> list = saleMapper.list();
        //设置文件下载
        //设置响应头，告诉浏览器要以附件的形式保存，filename=文件名
        response.setHeader("content-disposition", "attachment;filename=goods" + System.currentTimeMillis() + ".xlsx");
        EasyExcel.write(response.getOutputStream(), SaleListItemVO.class).sheet("商品详情").doWrite(list);
    }

    @Override
    public List<SaleListItemVO> list(){
        log.debug("开始处理查询商品列表业务");
        return saleMapper.list();
    }
    // 分页查询所有销售信息的方法
    // pageNum是页码
    // pageSize是每页条数
    //分页查询销售信息列表
    @Override
    public JsonPage<Sale> getAllSaleByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        log.debug("num = {},Size = {}",pageNum,pageSize);
        List<Sale> list = saleMapper.findAllSale();
        return JsonPage.restPage(new PageInfo<>(list));
    }

}
