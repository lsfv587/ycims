package com.yc.dao.entity.pub;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 15:17
 **/
@Data
public class pub_channelBind {
    private String id;
    private String channelCode;
    private String productCode;
    private Integer priority;
}
