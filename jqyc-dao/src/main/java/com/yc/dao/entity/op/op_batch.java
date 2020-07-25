package com.yc.dao.entity.op;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-21 12:18
 **/
@Data
public class op_batch {
    private String id;
    private String orderDate;
    private String pickDate;
    private Integer lineCode;
    private String batchCode;
    private Integer batchSequence;
    private Integer qty;
    private Integer custNum;
    private Integer custAvgQty;
}
