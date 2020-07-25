package com.yc.dao.entity.op;

import lombok.Data;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-21 12:16
 **/
@Data
public class op_order {
    private String id;
    private String batchId;
    private String orderDate;
    private String orderCode;
    private String custCode;
    private String pathCode;
    private String productCode;
    private Integer qty;
    private Integer lineCode;

    private Integer subLineQty;
    private Integer subLineCode;

}
