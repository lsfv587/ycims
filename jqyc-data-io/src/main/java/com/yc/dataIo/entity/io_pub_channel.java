package com.yc.dataIo.entity;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 11:53
 **/
@Data
public class io_pub_channel {
    private Integer id;
    private Integer lineCode;
    private Integer subLineCode;
    private String channelCode;
    private String channelName;
    private String channelType;
    private Integer carCode;
    private Integer mcsDownNo;
    private Integer isDynamic;
    private Integer tagaddr;
    private Integer tagid;
    private Integer safeStockQty;
    private Integer maxWidth;
    private Integer maxHeight;
    private Integer maxLength;
    private Integer minWidth;
    private Integer minHeight;
    private Integer minLength;
    private Integer state;

}
