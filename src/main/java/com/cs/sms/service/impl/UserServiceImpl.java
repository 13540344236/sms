package com.cs.sms.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs.sms.ex.ServiceException;
import com.cs.sms.mapper.RoleMapper;
import com.cs.sms.mapper.UserMapper;
import com.cs.sms.mapper.UserRoleMapper;
import com.cs.sms.pojo.dto.UserDTO;
import com.cs.sms.pojo.dto.UserLoginDTO;
import com.cs.sms.pojo.dto.UserPasswordDTO;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.User;
import com.cs.sms.pojo.entity.UserRole;
import com.cs.sms.pojo.vo.*;
//import com.cs.sms.security.UserDetails;
import com.cs.sms.service.IUserService;
import com.cs.sms.util.JwtUtils;
import com.cs.sms.web.JsonPage;
import com.cs.sms.web.ServiceCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
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
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

//    /**
//     * 处理登录验证
//     * @param userLoginDTO 封装了用户用户登录时提交的数据的对象
//     * @return
//     */
//    @Override
//    public String login(UserLoginDTO userLoginDTO) {
//        log.debug("开始处理用户登录的业务，参数：{}", userLoginDTO);
//
//        // 调用AuthenticationManager执行Spring Security的认证
//        Authentication authentication
//                = new UsernamePasswordAuthenticationToken(
//                userLoginDTO.getUsername(), userLoginDTO.getPassword());
//        Authentication loginResult = authenticationManager.authenticate(authentication);
//
//        // 以上调用的authenticate()方法是会抛出异常的方法，如果还能执行到此处，则表示用户名与密码是匹配的
//        log.debug("登录成功！认证方法返回的数据类型：{}", loginResult.getClass().getName());
//        log.debug("登录成功！认证方法返回的数据：{}", loginResult);
//        // 从认证结果中获取Principal，本质上是User类型，且是UserDetailsService中loadUserByUsername()返回的结果
//        log.debug("认认信息中的Principal类型：{}", loginResult.getPrincipal().getClass().getName());
//        log.debug("认认信息中的Principal数据：{}", loginResult.getPrincipal());
//        UserDetails userDetails = (UserDetails) loginResult.getPrincipal();
//        Long id = userDetails.getId();
//        log.debug("登录成功的用户id：{}", id);
//        String username = userDetails.getUsername();
//        log.debug("登录成功的用户用户名：{}", username);
//        Collection<GrantedAuthority> authorities = userDetails.getAuthorities();
//        log.debug("登录成功的用户权限：{}", authorities);
//        String authoritiesString = JSON.toJSONString(authorities);
//        log.debug("将用户权限转换为JSON：{}", authoritiesString);
//
//        // 应该在此处生成JWT数据，向JWT中存入：id（暂无）, username, 权限
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("id", userDetails.getId());
//        claims.put("username", userDetails.getUsername());
//        claims.put("authorities", authoritiesString);
//        String jwt = JwtUtils.generate(claims);
//        log.debug("生成JWT：{}", jwt);
//        return jwt;
//    }




    @Override
    public void addNew(UserDTO userDTO) {
        // 检查此用户（尝试创建的用户）的名称有没有被使用
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

        // 将用户数据写入到数据库中
        log.debug("即将向表中写入数据：{}", user);
        //将用户数据写入到数据库中
        int rows = userMapper.insert(user);
        log.debug("rows = {}",rows);
        if (rows != 1) {
            String message = "添加商品失败，服务器忙，请稍后再试！";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }

        // 插入用户与角色的关联数据，使得以上添加的用户是被分配了角色的
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId().longValue()); //设置用户id
        List<RoleVO> roleIds = roleMapper.getByName(userDTO.getRole());
        for (RoleVO roleId : roleIds) {
            userRole.setRoleId(roleId.getId());//匹配角色id
        }
        log.debug("即将向用户与角色的关联表中写入数据：{}", userRole);
        rows = userRoleMapper.insert(userRole);//向关联表中插入用户id和角色id
        if (rows != 1) {
            String message = "添加用户失败，服务器忙，请稍后再次尝试！【错误码：2】";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
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

        // 将用户数据写入到数据库中


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

    @Override
    public boolean login(UserDTO userDTO) {
        return false;
    }

    @Override
    public Object register(UserDTO userDTO) {
        return null;
    }
}
