package com.yc.dataIo.entity;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 15:13
 **/
@Data
public class io_pub_channelBind {
    private String channelCode;
    private String productCode;
    private Integer priority;
}
