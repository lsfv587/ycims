package com.yc.dao.entity.op;

import lombok.Data;

import java.lang.ref.PhantomReference;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-21 12:21
 **/
@Data
public class op_cust {
    private String id;
    private String custCode;
    private String batchId;
    private String custName;
    private String pathCode;
    private Integer custSequence;
    private String address;
    private String licenseCode;
    private String manager;
    private String phone;
    private Integer lineCode;
}
