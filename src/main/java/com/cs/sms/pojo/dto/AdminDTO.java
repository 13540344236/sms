package com.cs.sms.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class AdminDTO implements Serializable {
    /**
     * 员工id
     */
    private Long id;
    /**
     * 员工姓名
     */
    private String staffName;
    /**
     * 员工密码
     */
    private String password;
    /**
     * 员工性别
     */
    private String gender;
    /**
     * 员工电话号码
     */
    private String phone;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 描述
     */
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminDTO adminDTO = (AdminDTO) o;
        return Objects.equals(id, adminDTO.id) && Objects.equals(staffName, adminDTO.staffName) && Objects.equals(password, adminDTO.password) && Objects.equals(gender, adminDTO.gender) && Objects.equals(phone, adminDTO.phone) && Objects.equals(idNumber, adminDTO.idNumber) && Objects.equals(email, adminDTO.email) && Objects.equals(description, adminDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, staffName, password, gender, phone, idNumber, email, description);
    }
}