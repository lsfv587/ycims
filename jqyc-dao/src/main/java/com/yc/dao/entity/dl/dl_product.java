package com.yc.dao.entity.dl;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 15:04
 **/
@Data
public class dl_product {
    private String id;
    private String productCode;
    private String productName;
    private String isAbnor;
    private String isOverseas;
    private String barCode;
    private String jbarCode;
    private BigDecimal buyPrice;
    private BigDecimal tradePrice;
    private BigDecimal retailPrice;
    private BigDecimal costPrice;
    private Integer qty;
    private Integer isSort;
    private Integer lineCode;
}
