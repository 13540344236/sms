package com.cs.sms.service.impl;

import com.azul.crs.util.logging.Logger;
import com.cs.sms.ex.ServiceException;
import com.cs.sms.mapper.RoleMapper;
import com.cs.sms.pojo.dto.RoleDTO;
import com.cs.sms.pojo.entity.Admin;
import com.cs.sms.pojo.entity.Role;
import com.cs.sms.pojo.vo.RoleVO;
import com.cs.sms.service.IRoleService;
import com.cs.sms.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class RoleServicelmpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void addNew(RoleDTO roleDTO) {
        int select = roleMapper.select(roleDTO.getName());
        if (select>0){
            String message="添加失败,该角色已存在";
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }
        Role role=new Role();
        LocalDateTime dateTime = LocalDateTime.now();
        roleDTO.setGmtCreate(dateTime);
        BeanUtils.copyProperties(roleDTO,role);
        int insert = roleMapper.insert(role);
        if (insert != 1) {
            String message="服务器忙 请联系管理员";
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }
    }

    @Override
    public List<RoleVO> list() {
        return roleMapper.list();
    }

    @Override
    public void delete(Long id) {
        List<RoleVO> byId = roleMapper.getById(id);
        if (byId==null){
            String message="删除失败，该用户不存在";
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
        int i = roleMapper.deleteById(id);
        if (i!=1){
            String message="服务器忙 请联系管理员";
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }

    }

    @Override
    @Transactional
    public void updateById(Role role) {
        log.debug("需要修改的员工信息:{}", role);
        LocalDateTime dateTime = LocalDateTime.now();
        role.setGmtModified(dateTime);
        int row = roleMapper.updateById(role);
        if (row!=1){
            String message="修改失败,服务器忙，请稍后重试";
            throw new ServiceException(ServiceCode.ERR_UPDATE,message);
        }
    }

}
