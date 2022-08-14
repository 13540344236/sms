package com.cs.sms.service.impl;

import com.cs.sms.ex.ServiceException;
import com.cs.sms.mapper.MemberMapper;
import com.cs.sms.pojo.dto.MemberDTO;
import com.cs.sms.pojo.entity.Member;
import com.cs.sms.pojo.vo.MemberVO;
import com.cs.sms.service.IMemberService;
import com.cs.sms.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
public class MemberServiceImpI implements IMemberService {

    @Autowired
    MemberMapper memberMapper;

    public MemberServiceImpI(){log.debug("创建业务逻辑对象MemberServiceImpI");}

    @Transactional
    @Override
    public void addNew(MemberDTO memberDTO) {
        log.debug("接收需要新增的会员信息:{}",memberDTO);
        Long phone=memberDTO.getPhone();
        MemberVO memberVO = memberMapper.selectByPhone(phone);
        if(memberVO != null){
            String message="该用户已存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }
        Member member=new Member();
        BeanUtils.copyProperties(memberDTO,member);
        member.setMemberId(phone.toString());
        member.setIntegral(0L);
        member.setMoney(20.00);
        log.debug("尝试插入数据:{}",member);
        int insert = memberMapper.insert(member);
        if(insert != 1){
            String message="增加会员失败,服务器忙,请稍后在试";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_INSERT,message);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.debug("尝试删除的会员数据id:{}",id);
         MemberVO memberVO =memberMapper.selectById(id);
         if(memberVO == null){
             String message="尝试删除的数据不存在";
             log.error(message);
             throw new ServiceException(ServiceCode.ERR_DELETE,message);
         }
         int i = memberMapper.deleteById(id);
         if(i != 1){
             String message="删除数据失败,服务器忙,请稍后在试";
             log.error(message);
             throw new ServiceException(ServiceCode.ERR_DELETE,message);
         }
    }

    @Override
    public void updateById(MemberDTO memberDTO) {
        log.debug("接收需要修改的会员信息:{}",memberDTO);
        Member member=new Member();
        BeanUtils.copyProperties(memberDTO,member);
        int i = memberMapper.updateById(member);
        if(i != 1){
            String message="修改数据失败";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE,message);
        }
        log.debug("修改数据成功");
    }

    @Override
    public MemberVO selectByPhone(Long phone) {
        log.debug("接收需要查询的会员电话");
        MemberVO memberVO = memberMapper.selectByPhone(phone);
        if(memberVO == null){
            String message="该会员不存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
        return memberVO;
    }

    @Override
    public MemberVO selectById(Long id) {
        log.debug("接收需要查询的会员id");
        MemberVO memberVO = memberMapper.selectById(id);
        if(memberVO == null){
            String message="该会员不存在";
            log.error(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
        return memberVO;
    }

    @Override
    public List<MemberVO> list() {
        log.debug("开始查询所有会员信息");
        return memberMapper.list();
    }
}
