package com.cs.sms.pojo.entity;

import java.util.Date;

public class Sale {
    private Long id;

    private String name;

    private String goodsSpecification;

    private Integer saleQuantity;

    private String customerName;

    private String customerPhone;

    private String operatingStaff;

    private String purchaseDocumentPicture;

    private String logo;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public Integer getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(Integer saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getOperatingStaff() {
        return operatingStaff;
    }

    public void setOperatingStaff(String operatingStaff) {
        this.operatingStaff = operatingStaff;
    }

    public String getPurchaseDocumentPicture() {
        return purchaseDocumentPicture;
    }

    public void setPurchaseDocumentPicture(String purchaseDocumentPicture) {
        this.purchaseDocumentPicture = purchaseDocumentPicture;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}