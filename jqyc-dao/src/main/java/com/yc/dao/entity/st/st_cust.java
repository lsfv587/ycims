package com.yc.dao.entity.st;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-23 11:20
 **/
@Data
public class st_cust {
    private String id;
    private String custCode;
    private String custName;
    private Integer custSequence;
    private Integer serialNumber = 0;
    private String address;
    private String licenseCode;
    private String phone;
    private String manager;
    private String pathCode;
    private String batchId;
}
