package com.yc.dao.entity.pub;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 12:05
 **/
@Data
public class pub_product {
    private String id;
    private String productCode;
    private String productName;
    private String productType;
    private Integer productWidth;
    private Integer productHeight;
    private Integer productLength;
    private String isOverseas;
    private String barCode;
    private String jbarCode;
    private Integer jqty;
    private Integer stackQty;

    private BigDecimal buyPrice;
    private BigDecimal tradePrice;
    private BigDecimal retailPrice;
    private BigDecimal costPrice;

}
