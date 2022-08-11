package com.cs.sms.pojo.dto;

import lombok.Data;

@Data
public class MemberDTO {
    /**
     * 会员ID
     */
    private Long id;
    /**
     * 会员姓名
     */
    private String name;
    /**
     * 会员电话
     */
    private String phone;
    /**
     * 支付方式
     */
    private Byte paymentMethod;
    /**
     * 购买商店地址
     */
    private String address;
}
