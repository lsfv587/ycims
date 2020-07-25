package com.yc.dao.entity.st;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-23 11:31
 **/
@Data
public class st_channel {
    private String id;
    private String channelCode;
    private String productCode;
    private Integer entryQty;
    private Integer stockQty;
    private String batchId;

    private String channelName;
    private String productName;
    private Integer subLineCode;
    private Integer mcsDownNo;
    private Integer isDynamic;
    private Integer totalQty;
}
