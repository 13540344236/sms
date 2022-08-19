package com.cs.sms.pojo.dto;

import lombok.Data;

@Data
public class LoginPasswordDTO {
    private String username;
    private String password;
    private String newPassword;
}
