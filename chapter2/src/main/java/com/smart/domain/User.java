package com.smart.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Integer userId;
    private String userName;
    private String password;
    private Integer credits;
    private String lastIp;
    private Date lastVisit;
}
