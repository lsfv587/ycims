package com.yc.dao.entity.op;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 09:27
 **/
@Data
public class op_product {
    private String id;
    private String productCode;
    private String batchId;
    private Integer lineCode;
    private String productName;
    private Integer productWidth;
    private Integer productHeight;
    private Integer productLength;
    private Integer productType;
    private Integer isAbnor;
    private Integer isOverseas;
    private String barCode;
    private String jbarCode;
    private Integer jqty;
    private Integer qty;
    private BigDecimal buyPrice;
    private BigDecimal tradePrice;
    private BigDecimal retailPrice;
    private BigDecimal costPrice;
    private Integer stockQty = 0;

    private Integer prepareQty = 0;
    private boolean isBand = false;
    private Integer opQty;
}
