package com.cs.sms.service.impl;



import com.cs.sms.ex.ServiceException;
import com.cs.sms.mapper.PurchaseMapper;
import com.cs.sms.pojo.dto.PurchaseAddNewDTO;
import com.cs.sms.pojo.entity.Purchase;
import com.cs.sms.pojo.vo.PurchaseDetailVO;
import com.cs.sms.pojo.vo.PurchaseListItemVO;
import com.cs.sms.repo.IPurchaseRepository;
import com.cs.sms.service.IPurchaseService;
import com.cs.sms.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class PurchaseServiceImpl implements IPurchaseService {
    @Autowired
    private IPurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;
    public PurchaseServiceImpl() {
        log.debug("创建业务逻辑对象：PurchaseServiceImpl");
    }

    @Override
    public void addNew(PurchaseAddNewDTO purchaseAddNewDTO) {
        log.debug("开始处理添加商品的业务，参数：{}",purchaseAddNewDTO);

        String name = purchaseAddNewDTO.getName();
        int count = purchaseMapper.countByName(name);
        if (count > 0) {
            String message = "添加商品失败，品牌名称【" + name + "】已经被占用！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        // 创建实体对象（Mapper的方法的参数是实体类型）
        Purchase purchase = new Purchase();

        // 将当前方法参数的值复制到purchase实体类型的对象中
        BeanUtils.copyProperties(purchaseAddNewDTO, purchase);
        // 将品牌数据写入到数据库中
        purchase.setGmtCreate(new Date());
        log.debug("即将向表中写入数据：{}", purchase);
        int rows = purchaseMapper.insert(purchase);
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
        PurchaseDetailVO queryResult = purchaseMapper.getById(id);
        // 判断查询结果是否为null
        if (queryResult == null) {
            // 抛出异常
            String message = "删除商品失败，尝试删除的数据（id=" + id + "）不存在！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 调用mapper对象执行删除，并获取返回值
        int rows = purchaseMapper.deleteByPrimaryKey(id);
        // 判断返回值是否不为1
        if (rows != 1) {
            // 抛出异常
            String message = "删除商品失败，服务器忙，请稍后再次尝试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
    }

    @Override
    public List<PurchaseListItemVO> list() {
        log.debug("开始处理查询商品列表的业务");
        return purchaseMapper.list();

    }
}
