package com.cs.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cs.sms.pojo.dto.UserPasswordDTO;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.User;
import com.cs.sms.pojo.vo.GoodsDetailVO;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.pojo.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  Mapper 接口
 */
@Repository
public interface UserMapper  {

    /**
     * 插入用户列表数据
     * @param
     * @return 受影响的行数，成功插入数据时，将返回1
     */
    int insert(User user);

    /**
     * 根据用户ID删除用户列表数据
     * @param id 用户编号(ID)
     * @return 受影响的行数，成功删除数据时，将返回1
     */
    int deleteById(Long id);

    /**
     * 根据用户ID删除用户列表数据
     * @param ids 用户编号(ID)
     * @return 受影响的行数，成功删除数据时，将返回1
     */
    int deleteByIds(Long[] ids);

    /**
     * 根据用户ID修改用户列表数据
     * @param
     * @return 受影响的行数，成功修改数据时，将返回1
     */
    int updateById(User user);

    /**
     * 根据ID查询用户数据
     * @param id 用户编号(ID)
     * @return 成功查询到用户后,返回查询到的用户数据
     */
    UserVO selectById(Long id);

    /**
     * 根据用户名查询用户列表
     * @param name
     * @return
     */
    List<UserVO> selectByName(String name);

    /**
     * 根据用户名称统计用户数量
     * @param name 用户名称
     * @return 用户数量
     */
    int countByName(String name);

    /**
     *  查看用户列表
     * @return 用户列表
     */
    List<UserVO> list();



    /**
     * 分页查询所有用户
     * @return 用户列表
     */
    List<User> findAllUser();
}
