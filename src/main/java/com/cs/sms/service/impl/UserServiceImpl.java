package com.cs.sms.service.impl;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.sms.ex.ServiceException;
import com.cs.sms.mapper.UserMapper;
import com.cs.sms.pojo.dto.UserDTO;
import com.cs.sms.pojo.dto.UserPasswordDTO;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.User;
import com.cs.sms.pojo.vo.AdminVO;
import com.cs.sms.pojo.vo.GoodsDetailVO;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.pojo.vo.UserVO;
import com.cs.sms.service.IUserService;
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
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 *
 *  用户实现类
 *
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public void addNew(UserDTO userDTO) {
        // 检查此品牌（尝试创建的品牌）的名称有没有被使用
        // 如果已经被使用，则不允许创建
        String name = userDTO.getUsername();
        int count = userMapper.countByName(name);
        log.debug("count = {}",count);
        if (count > 0){
            String message = "增加商品失败，商品名称【"+ name + "】已被占用";
            throw new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }

        //创建实体对象 (Mapper的方法的参数是实体类)
        User user = new User();
        //将当前方法参数的值复制到Goods实体类型的对象中
        BeanUtils.copyProperties(userDTO,user);

        // 将品牌数据写入到数据库中
        log.debug("即将向表中写入数据：{}", user);
        //将品牌数据写入到数据库中
        int rows = userMapper.insert(user);
        log.debug("rows = {}",rows);
        if (rows != 1) {
            String message = "添加商品失败，服务器忙，请稍后再试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }
    }

    //删除用户信息
    @Override
    public void deleteById(Long id) {
        //根据id查询数据
        UserVO userVO = userMapper.selectById(id);

        //判断查询结果是否为null
        if (userVO == null) {
            //抛出异常
            String message = "删除商品失败，尝试删除的数据【id =" + id + "】的数据不存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        //调用mapper对象执行删除，并获取返回值
        int rows = userMapper.deleteById(id);
        //判断返回值是否不为1
        if (rows != 1) {
            //抛出异常
            String message = "删除商品失败，服务器忙，请稍后再次尝试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
    }

    //编辑用户信息
    @Override
    public void updateById(UserDTO userDTO) {
        //根据id查询数据(前端传回来的userDTO包含id，故需要取出id【userDTO.getId().longValue()】)
        UserVO userVO = userMapper.selectById(userDTO.getId().longValue());

        log.debug("查询到id = {}的商品信息为:{}", userDTO.getId().longValue(),userVO);
        //判断查询结果是否为null
        if (userVO == null) {
            //抛出异常
            String message = "编辑商品失败，尝试编辑的数据【id ="+ userDTO.getId().longValue()+"】的数据不存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }

        User user = new User();

        //将当前方法参数的值复制到Goods实体类型的对象中
        BeanUtils.copyProperties(userDTO,user);

        // 将品牌数据写入到数据库中


        //调用mapper对象执行编辑，并获取返回值
        int rows = userMapper.updateById(user);
        log.debug("rows = {}",rows);
        //判断返回值是否不为1
        if (rows != 1) {
            //抛出异常
            String message = "编辑商品失败，服务器忙，请稍后再次尝试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_DELETE,message);
        }
    }

    @Override
    public UserVO selectById(Long id) {
        return null;
    }

    @Override
    public UserVO selectByName(String name) {
        return null;
    }

    @Override
    public List<UserVO> list() {
        return userMapper.list();
    }

    @Override
    public JsonPage<User> getAllUserByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        log.debug("num = {},Size = {}",pageNum,pageSize);
        List<User> list = userMapper.findAllUser();
        return JsonPage.restPage(new PageInfo<>(list));
    }

    @Override
    public void createExcel(HttpServletResponse response) throws IOException {
        //1.查询到商品的所有信息
        List<UserVO> list = userMapper.list();
        //2.设置文件下载
        //设置响应头，告诉浏览器要以附件的形式保存，filename=文件名
        response.setHeader("content-disposition","attachment;filename=users"+System.currentTimeMillis()+".xlsx");
        EasyExcel.write(response.getOutputStream(), UserVO.class).sheet("商品详情").doWrite(list);

    }
}
