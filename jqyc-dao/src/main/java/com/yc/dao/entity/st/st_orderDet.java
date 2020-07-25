package com.yc.dao.entity.st;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-23 11:46
 **/
@Data
public class st_orderDet {
    private String id;
    private String channelCode;
    private String productCode;
    private Integer qty;
    private String orderMasId;
}
