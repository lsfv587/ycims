package com.yc.dataIo.entity;

import lombok.Data;

/**
 * @program: jqyc
 * @description: 接口数据客户表
 * @author: lsf
 * @create: 2020-07-14 11:31
 **/
@Data
public class io_cust {
    private String custCode;
    private String custName;
    private String pathCode;
    private Integer custSequence;
    private String address;
    private String licenseCode;
    private String manager;
    private String phone;
    private Integer lineCode;
}
