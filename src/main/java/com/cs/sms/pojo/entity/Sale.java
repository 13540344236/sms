package com.cs.sms.pojo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Data
public class Sale {
    private Long id;

    private String name;

    private String goodsSpecification;

    private Integer saleQuantity;
    /**
     *  采购价格
     */
    private BigDecimal purchasePrice;

    /**
     * 销售价格
     */
    private BigDecimal salePrice;

    private String customerName;

    private String customerPhone;

    private String operatingStaff;

    private String purchaseDocumentPicture;

    private String logo;

    private Date gmtCreate;

    private Date gmtModified;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id) && Objects.equals(name, sale.name) && Objects.equals(goodsSpecification, sale.goodsSpecification) && Objects.equals(saleQuantity, sale.saleQuantity) && Objects.equals(purchasePrice, sale.purchasePrice) && Objects.equals(salePrice, sale.salePrice) && Objects.equals(customerName, sale.customerName) && Objects.equals(customerPhone, sale.customerPhone) && Objects.equals(operatingStaff, sale.operatingStaff) && Objects.equals(purchaseDocumentPicture, sale.purchaseDocumentPicture) && Objects.equals(logo, sale.logo) && Objects.equals(gmtCreate, sale.gmtCreate) && Objects.equals(gmtModified, sale.gmtModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, goodsSpecification, saleQuantity, purchasePrice, salePrice, customerName, customerPhone, operatingStaff, purchaseDocumentPicture, logo, gmtCreate, gmtModified);
    }
}