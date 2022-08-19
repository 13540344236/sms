package com.cs.sms.pojo.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Log implements Serializable {
    private Long id;
    private String ip;
    private String username;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;
    private String operation;
    private String method;
    private String params;
    private Long time;
    private Integer status;
    private String error;

}
