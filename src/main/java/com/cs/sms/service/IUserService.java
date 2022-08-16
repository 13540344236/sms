package com.cs.sms.service;

import com.cs.sms.pojo.dto.UserDTO;
import com.cs.sms.pojo.entity.User;
import com.cs.sms.pojo.vo.AdminVO;
import com.cs.sms.pojo.vo.UserVO;
import com.cs.sms.web.JsonPage;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 *  服务类
 */
public interface IUserService  {
    /**
     * 增加用户
     * @param userDTO
     */
    void addNew(UserDTO userDTO);

    /**
     * 根据ID删除对应数据
     * @param id 用户id
     */
    void deleteById(Long id);

    /**
     * 根据id修改用户信息
     * @param userDTO
     */
    void updateById(UserDTO userDTO);

    /**
     * 根据id查询到的用户信息
     * @return
     */
    UserVO selectById(Long id);

    /**
     * 根据用户姓名查询相关数据
     * @param name 用户姓名
     * @return
     */
    UserVO selectByName(String name);

    /**
     * 查询所有用户信息
     * @return
     */
    List<UserVO> list();

    // 分页查询
    JsonPage<User> getAllUserByPage(Integer pageNum, Integer pageSize);

    /**
     * 导出用户报表
     * @param response
     * @throws IOException
     */
    void createExcel(HttpServletResponse response) throws IOException;
    
}
