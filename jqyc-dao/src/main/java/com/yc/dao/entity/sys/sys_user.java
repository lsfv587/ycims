package com.yc.dao.entity.sys;

import lombok.Data;

import java.util.Date;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-27 10:19
 **/
@Data
public class sys_user {
    private String id;
    private String username;
    private String password;
    private Integer isLock;
    private Date createTm;
}
