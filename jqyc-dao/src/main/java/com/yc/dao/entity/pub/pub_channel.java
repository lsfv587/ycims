package com.yc.dao.entity.pub;

import lombok.Data;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 10:57
 **/
@Data
public class pub_channel {
    private String id;
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
    private Integer stockModel;
    private pub_subLine subLine;

}
