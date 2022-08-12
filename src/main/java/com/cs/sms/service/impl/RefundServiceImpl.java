package com.cs.sms.service.impl;

import com.alibaba.excel.EasyExcel;
import com.cs.sms.ex.ServiceException;
import com.cs.sms.mapper.RefundMapper;
import com.cs.sms.pojo.dto.GoodsEditDTO;
import com.cs.sms.pojo.dto.PurchaseEditDTO;
import com.cs.sms.pojo.dto.RefundAddNewDTO;
import com.cs.sms.pojo.dto.RefundEditDTO;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.Purchase;
import com.cs.sms.pojo.entity.Refund;
import com.cs.sms.pojo.vo.*;
import com.cs.sms.repo.impl.RefundRepositoryImpl;
import com.cs.sms.service.IRefundService;
import com.cs.sms.web.ServiceCode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class RefundServiceImpl implements IRefundService {
    @Autowired
    private RefundMapper refundMapper;

    @Autowired
    private RefundRepositoryImpl refundRepository;

    public RefundServiceImpl(){
        log.debug("创建业务逻辑对象：RefundServiceImpl");
    }
    //添加商品
    @Override
    public void addNew(RefundAddNewDTO refundAddNewDTO) {
        String name = refundAddNewDTO.getName();
        int count = refundMapper.countByName(name);
        log.debug("count = {}",count);
        if (count>0){
            String message = "添加商品失败，品牌名称【" + name + "】已经被占用！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        // 创建实体对象（Mapper的方法的参数是实体类型）
        Refund refund = new Refund();

        // 将当前方法参数的值复制到purchase实体类型的对象中
        BeanUtils.copyProperties(refundAddNewDTO, refund);
        LocalDateTime dateTime = LocalDateTime.now();
        refund.setGmtCreateReturn(dateTime);

        // 将品牌数据写入到数据库中
        log.debug("即将向表中写入数据：{}", refund);
        //将品牌数据写入到数据库中
        int rows = refundMapper.insert(refund);
        log.debug("rows = {}",rows);
        if (rows != 1) {
            String message = "添加商品失败，服务器忙，请稍后再次尝试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }


    //删除商品
    @Override
    public void deleteByPrimaryKey(Long id) {

        // 根据id查询数据
       RefundDetailVO refundDetailVO = refundMapper.getById(id);

        // 判断查询结果是否为null
        if (refundDetailVO == null) {
            // 抛出异常
            String message = "删除商品失败，尝试删除的数据（id=" + id + "）不存在！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 调用mapper对象执行删除，并获取返回值
        int rows = refundMapper.deleteByPrimaryKey(id);
        // 判断返回值是否不为1
        if (rows != 1) {
            // 抛出异常
            String message = "删除商品失败，服务器忙，请稍后再次尝试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
    }


    //编辑商品
    @Override
    public void update(Long id, RefundEditDTO refundEditDTO) {
        //根据id查询数据
        RefundDetailVO refundDetailVO = refundMapper.getById(id);
        log.debug("查询到id={}的商品信息为:{}", id, refundDetailVO);
        //判断查询结果是否为null
        if (refundDetailVO == null) {
            //抛出异常
            String message = "编辑商品失败，尝试编辑的数据【id=" + id + "】的数据不存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
       Refund refund = new Refund();
        //将当前方法参数的值复制到refund实体类型的对象中
        BeanUtils.copyProperties(refundEditDTO, refund);
        //将品牌数据写入到数据库中
        log.debug("即将向表中写入数据:{}",refundEditDTO);
        log.debug("接收到的参数id:{}", id);
        //调用mapper对象执行编辑，并获取返回值
        int rows = refundMapper.updateById(refund);
        log.debug("rows = {}", rows);
        //判断返回值是否为1
        if (rows != 1) {
            String message = "编辑商品失败，服务器忙，请稍后再尝试!";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
    }


    @Override
    public List<RefundListItemVO> list() {
        return refundMapper.list();
    }

    @Override
    public void deleteById(Long id) {

    }
    //导出报表
    @Override
    public void createExcel(HttpServletResponse response) throws IOException {
        //1.查询到商品的所有信息
        List<RefundListItemVO> list =refundMapper.list();
        //2.设置文件下载
        //设置响应头，告诉浏览器要以附件的形式保存，filename=文件名
        response.setHeader("content-disposition","attachment;filename=refunds"+System.currentTimeMillis()+".xlsx");
        EasyExcel.write(response.getOutputStream(), RefundListItemVO.class).sheet("商品详情").doWrite(list);
    }
}

