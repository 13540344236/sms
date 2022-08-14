package com.cs.sms.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.beust.ah.A;
import com.cs.sms.ex.ServiceException;
import com.cs.sms.mapper.AdminMapper;
import com.cs.sms.pojo.dto.AdminDTO;
import com.cs.sms.pojo.entity.Admin;
import com.cs.sms.pojo.vo.AdminVO;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.repo.IAlbumRepository;
import com.cs.sms.service.IAdminService;
import com.cs.sms.web.Results;
import com.cs.sms.web.ServiceCode;
import lombok.Data;
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
public class AdminServiceImpl implements IAdminService {

    @Autowired
    AdminMapper adminMapper;

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
        //设置管理员的创建时间为当前系统时间
        admin.setGmtCreate(new Date());
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

    @Override
    public void createExcel(HttpServletResponse response) throws IOException {
        //1.查询到商品的所有信息
        List<AdminVO> list = adminMapper.list();
        //2.设置文件下载
        //设置响应头，告诉浏览器要以附件的形式保存，filename=文件名
        response.setHeader("content-disposition","attachment;filename=staffs"+System.currentTimeMillis()+".xlsx");
        EasyExcel.write(response.getOutputStream(), AdminVO.class).sheet("商品详情").doWrite(list);

    }

    @Override
    public Results<Object> upload(MultipartFile file) {
        if (file == null) new Results<>(404, "导入数据失败", null);
        ArrayList<Object> list = new ArrayList<>();
        AnalysisEventListener listener = new AnalysisEventListener() {
            @Override
            public void invoke(Object data, AnalysisContext context) {
                //获取到每一行数据，逐行进行处理
                list.add(data);
                AdminVO clueVO = (AdminVO) data;
                Admin admin = new Admin();
                BeanUtils.copyProperties(clueVO, admin);
                //这里将获取到的数据封装回实体类对象中，并在数据库持久化
                adminMapper.ExcelInsert(admin);
                System.out.println(Arrays.toString(new ArrayList[]{list}));
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                log.info("导入数据完毕");
            }
        };
        try {
            EasyExcel.read(file.getInputStream(), AdminVO.class, listener).sheet(0).doRead();
        } catch (IOException e) {
            log.error("导入出错：{}", e.getMessage());
        }
        return new Results<>(200, "导入数据成功", list);
    }
}
