package com.yc.dao.entity.op;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-22 11:25
 **/
@Data
public class op_productLoad {
    private String id;
    private String productCode;
    private String batchId;
    private Integer qty;
    private Integer prepareQty = 0;
    private Integer lineCode;
    private Integer jqty;

    private Integer productWidth;
    private Integer productHeight;
    private Integer productLength;
    private Integer productType;
    private Integer isAbnor;
    private Integer opQty = 0;
    private Integer stockQty = 0;
    private boolean isBand = false;
}
