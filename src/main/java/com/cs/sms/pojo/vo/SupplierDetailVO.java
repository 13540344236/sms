package com.cs.sms.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
public class SupplierDetailVO implements Serializable {
    /**
     *  id
     */
    private Long id;

    /**
     * 供应商名字
     */
    private String supplier;

    /**
     * 供应商联系人名称
     */
    private String supplierName;

    /**
     *  供应商电话
     */
    private String supplierPhone;

    /**
     * 供应商地址
     */
    private String supplierSite;

    /**
     * 备注
     */
    private String remark;

    /**
     * 录入时间
     */
    private Date gmtCreate;
}
