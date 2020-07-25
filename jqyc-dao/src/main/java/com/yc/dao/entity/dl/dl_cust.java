package com.yc.dao.entity.dl;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 15:03
 **/
@Data
public class dl_cust {
    private String id;
    private String custCode;
    private String custName;
    private String pathCode;
    private Integer custSequence;
    private String address;
    private String licenseCode;
    private String manager;
    private String phone;
    private Integer isSort;
    private Integer lineCode;
}
