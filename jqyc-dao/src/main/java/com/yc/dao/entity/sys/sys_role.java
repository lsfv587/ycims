package com.yc.dao.entity.sys;

import lombok.Data;

import java.util.Date;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-27 10:21
 **/
@Data
public class sys_role {
    private String id;
    private String name;
    private String description;
    private Date createTm;
}
