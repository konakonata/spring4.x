package com.smart.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LoginLog implements Serializable {
    private Integer loginLogId;
    private Integer userId;
    private String ip;
    private Date loginDate;
}
