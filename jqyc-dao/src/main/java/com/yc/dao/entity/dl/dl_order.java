package com.yc.dao.entity.dl;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 15:02
 **/
@Data
public class dl_order {
    private String id;
    private String orderCode;
    private String orderDate;
    private String batchCode;
    private String custCode;
    private String pathCode;
    private String productCode;
    private Integer qty;

    private String channelCode;
    private Integer lineCode;
    private Integer subLineCode;
}
