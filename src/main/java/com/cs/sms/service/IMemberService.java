package com.cs.sms.service;

import com.cs.sms.pojo.dto.MemberDTO;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.Member;
import com.cs.sms.pojo.vo.MemberVO;
import com.cs.sms.web.JsonPage;

import java.util.List;

public interface IMemberService {
    /**
     * 增加会员
     * @param memberDTO
     */
    void addNew(MemberDTO memberDTO);

    /**
     * 根据ID删除对应数据
     * @param id 会员id
     */
    void deleteById(Long id);

    /**
     * 根据id修改会员信息
     * @param memberDTO
     */
    void updateById(MemberDTO memberDTO);

    /**
     * 根据phone查询到的会员信息
     * @return
     */
    MemberVO selectByPhone(Long phone);

    /**
     * 根据id查询会员信息
     * @param id
     * @return
     */
    MemberVO selectById(Long id);
    /**
     * 查询所有员工信息
     * @return
     */
    List<MemberVO> list();

    // 分页查询
    JsonPage<Member> getAllMemberByPage(Integer pageNum, Integer pageSize);
}
