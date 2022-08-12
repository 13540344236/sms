package com.cs.sms.mapper;

import com.cs.sms.pojo.entity.Member;
import com.cs.sms.pojo.vo.MemberVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMapper {

    /**
     * 新增会员
     * @return 返回受影响地行数
     */
    int insert(Member member);

    /**
     * 根据id删除会员
     * @param id
     * @return 返回受影响地行数
     */
    int deleteById(Long id);

    /**
     * 根据id修改会员信息
     *
     * @param member 封装了新的值的对象
     * @return 受影响的行数，当修改成功时，将返回1，如果无此id对应的数据，将返回0
     */
    int updateById(Member member);

    /**
     * 根据电话号码查询会员信息
     * @param phone 会员信息
     * @return 查询成功时返回查询到的数据
     */
    MemberVO selectByPhone(long phone);

    /**
     * 根据id查询会员信息
     * @return
     */
    MemberVO selectById(Long id);
    /**
     * 查询所有会员信息
     * @return 查询成功返回所有会员信息
     */
    List<MemberVO> list();

}
