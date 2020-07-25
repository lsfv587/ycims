package com.yc.dao.entity.op;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-22 14:54
 **/
@Data
public class op_orderChannel {
    private String id;
    private String orderId;
    private String channelCode;
    private String productCode;
    private Integer channelQty;
    private Integer subLineCode;
    private String orderCode;
}
