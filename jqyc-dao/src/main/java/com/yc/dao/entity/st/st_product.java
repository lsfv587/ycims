package com.yc.dao.entity.st;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-23 11:39
 **/
@Data
public class st_product {
    private String id;
    private String productCode;
    private Integer qty;
    private Integer prepareQty;
    private String batchId;
    private String productName;
    private Integer productWidth;
    private Integer productHeight;
    private Integer productLength;
    private BigDecimal buyPrice;
    private BigDecimal tradePrice;
    private BigDecimal retailPrice;
    private BigDecimal costPrice;
}
