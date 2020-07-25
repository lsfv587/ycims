package com.yc.dataIo.entity;

import lombok.Data;

/**
 * @program: jqyc
 * @description: 接口数据订单信息
 * @author: lsf
 * @create: 2020-07-14 11:36
 **/
@Data
public class io_order {
    private String orderCode;
    private String orderDate;
    private String batchCode;
    private String custCode;
    private String pathCode;
    private String productCode;
    private Integer qty;
    private Integer lineCode;
}
