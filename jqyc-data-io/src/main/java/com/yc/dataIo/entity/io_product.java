package com.yc.dataIo.entity;

import lombok.Data;

/**
 * @program: jqyc
 * @description: 接口数据--产品信息
 * @author: lsf
 * @create: 2020-07-14 11:41
 **/
@Data
public class io_product {
    private String productCode;
    private String productName;
    private String isAbnor;
    private String isOverseas;
    private String barCode;
    private String jbarCode;
    private String buyPrice;
    private String tradePrice;
    private String retailPrice;
    private String costPrice;
    private Integer lineCode;
}
