package com.cs.sms.pojo.entity;

import java.util.Date;

public class Purchase {
    private Long id;

    private String name;

    private String goodsCategory;

    private String goodsSpecification;

    private Integer warehousingQuantity;

    private String supplier;

    private String operator;

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

    public String getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(String goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public Integer getWarehousingQuantity() {
        return warehousingQuantity;
    }

    public void setWarehousingQuantity(Integer warehousingQuantity) {
        this.warehousingQuantity = warehousingQuantity;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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