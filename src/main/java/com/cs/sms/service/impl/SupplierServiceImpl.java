package com.cs.sms.service.impl;



import com.cs.sms.ex.ServiceException;
import com.cs.sms.mapper.SupplierMapper;
import com.cs.sms.pojo.dto.SupplierAddNewDTO;
import com.cs.sms.pojo.entity.Supplier;
import com.cs.sms.pojo.vo.SupplierListVO;
import com.cs.sms.repo.ISupplierRepository;
import com.cs.sms.service.ISupplierService;
import com.cs.sms.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class SupplierServiceImpl implements ISupplierService {

    @Autowired
    private SupplierMapper supplierMapper;
    public SupplierServiceImpl() {
        log.debug("创建业务逻辑对象：SupplierServiceImpl");
    }

    @Override
    public void addNew(SupplierAddNewDTO supplierAddNewDTO) {
        int row = supplierMapper.countByName(supplierAddNewDTO.getSupplier());
        if (row>0){
            String message="添加失败,该商家已经存在";
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        Supplier supplier=new Supplier();
        supplier.setGmtCreate(date);
        BeanUtils.copyProperties(supplierAddNewDTO,supplier);
        int rows = supplierMapper.insert(supplier);
        if(rows!=1){
            String message="添加失败,服务器忙，请稍后重试";
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }
    }

    @Override
    public void delete(Long id) {
        SupplierListVO byId = supplierMapper.getById(id);
        if (byId==null){
            String message="删除失败，该商家不存在";
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
        int row = supplierMapper.deleteByPrimaryKey(id);
        if (row!=1){
            String message="删除失败,服务器忙，请稍后重试";
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
    }

    @Override
    public List<SupplierListVO> list() {
        return supplierMapper.list();
    }

    @Override
    public void update(Supplier supplier) {
        int rows = supplierMapper.updateByPrimaryKeySelective(supplier);
        if (rows!=1){
            String message="修改失败,服务器忙，请稍后重试";
            throw new ServiceException(ServiceCode.ERR_UPDATE,message);
        }
    }

}
