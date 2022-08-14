package com.cs.sms.pojo.vo;

import lombok.Data;

@Data
public class MemberVO {
    /**
     * 会员ID
     */
    private Long id;
    /**
     * 会员号
     */
    private String memberId;
    /**
     * 会员姓名
     */
    private String name;
    /**
     * 会员电话
     */
    private Long phone;
    /**
     * 会员可用积分
     */
    private Long integral;
    /**
     * 用户金额
     */
    private Double money;
    /**
     * 支付方式
     */
    private String paymentMethod;
    /**
     * 购买商店地址
     */
    private String address;
}
