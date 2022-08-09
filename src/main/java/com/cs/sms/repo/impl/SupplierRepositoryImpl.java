package com.cs.sms.repo.impl;

import com.cs.sms.pojo.vo.SupplierListVO;
import com.cs.sms.repo.ISupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class SupplierRepositoryImpl implements ISupplierRepository {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    public SupplierRepositoryImpl() {
        log.debug("创建数据访问对象：SupplierRepositoryImpl");
    }

    @Override
    public void put(SupplierListVO supplierListVO) {
        String key=KEY_PREFIX_SUPPLIER_ITEM+supplierListVO.getId();
        redisTemplate.opsForValue().set(key,supplierListVO);
    }

    @Override
    public SupplierListVO get(Long id) {
        SupplierListVO supplier=null;
        String key=KEY_PREFIX_SUPPLIER_ITEM+id;
        Serializable serializable = redisTemplate.opsForValue().get(key);
        if (serializable!=null){
            if (serializable instanceof Serializable){
                supplier= (SupplierListVO) serializable;
            }
        }
        return supplier;
    }

    @Override
    public void deleteItem(Long id) {
        String key=KEY_PREFIX_SUPPLIER_ITEM+id;
        redisTemplate.delete(key);
    }

    @Override
    public void putList(List<SupplierListVO> list) {
        for (SupplierListVO supplierListVO : list) {
            redisTemplate.opsForList().rightPush(KEY_SUPPLIER_LIST, supplierListVO);
        }
    }

    @Override
    public List<SupplierListVO> getList() {
        List<Serializable> list = redisTemplate.opsForList().range(KEY_SUPPLIER_LIST, 0, -1);
        List<SupplierListVO> supplier = new ArrayList<>();
        for (Serializable serializable : list) {
            supplier.add((SupplierListVO) serializable);
        }
        return supplier;
    }

    @Override
    public void deleteList() {
        redisTemplate.delete(KEY_SUPPLIER_LIST);
    }

}
