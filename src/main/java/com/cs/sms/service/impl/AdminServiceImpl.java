package com.cs.sms.service.impl;

import com.cs.sms.ex.ServiceException;
import com.cs.sms.mapper.AdminMapper;
import com.cs.sms.pojo.dto.AdminDTO;
import com.cs.sms.pojo.entity.Admin;
import com.cs.sms.pojo.vo.AdminVO;
import com.cs.sms.repo.IAlbumRepository;
import com.cs.sms.service.IAdminService;
import com.cs.sms.web.ServiceCode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Data
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private IAlbumRepository albumRepository;

    @Autowired
    AdminMapper adminMapper;

    public AdminServiceImpl() {
        log.debug("创建业务逻辑对象：AlbumServiceImpl");
    }

    @Transactional
    @Override
    public void addNew(AdminDTO adminDTO) {
        log.debug("尝试插入数据:{}",adminDTO);
        String name=adminDTO.getStaffName();
        AdminVO adminVO = adminMapper.selectByName(name);
        if(adminVO != null){
            String message="该用户已存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }
        Admin admin=new Admin();
        BeanUtils.copyProperties(adminDTO,admin);
        admin.setOnDuty((byte)1);
        admin.setEnable((byte)1);
        admin.setLastLoginIp("0");
        admin.setLoginCount(0);
        log.debug("尝试插入数据:{}",admin);
        int rows = adminMapper.insert(admin);
        if(rows!=1){
            String message="增加员工失败,服务器忙,请稍后在试";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.debug("需要输出的员工id:{}",id);
        AdminVO adminVO=adminMapper.selectById(id);
        if(adminVO==null){
            String message="尝试删除的数据不存在,请稍后在试";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }

        int rows = adminMapper.deleteById(id);
        if(rows != 1){
            String message="删除员工失败,服务器忙,请稍后在试";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
    }

    @Override
    @Transactional
    public void updateById(AdminDTO adminDTO) {
        log.debug("需要修改的员工信息:{}",adminDTO);
        Admin admin=new Admin();
        BeanUtils.copyProperties(adminDTO,admin);
        int rows = adminMapper.updateById(admin);
        if(rows!=1){
            String message="尝试修改数据失败,服务器忙请稍后尝试";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE,message);
        }
    }

    @Override
    public AdminVO selectById(Long id) {
        log.debug("需要查询的员工信息的id:{}",id);
        AdminVO adminVO = adminMapper.selectById(id);
        if(adminVO != null){
            return adminVO;
        }else {
            String message="该员工不存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
    }
    @Override
    public AdminVO selectByName(String staffName) {
        log.debug("传入的员工姓名:{}",staffName);
        AdminVO adminVOS = adminMapper.selectByName(staffName);
        if(adminVOS != null){
            return adminVOS;
        }else {
            String message="该员工不存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
    }

    @Override
    public List<AdminVO> list() {
        return adminMapper.list();
    }
}
