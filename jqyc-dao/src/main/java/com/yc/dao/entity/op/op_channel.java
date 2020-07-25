package com.yc.dao.entity.op;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 10:34
 **/
@Data
public class op_channel {
    private String id;
    private String batchId;
    private String channelCode;
    private String productCode;
    private Integer stockQty = 0;
    private Integer qty = 0;
    private Integer lineCode;
    private Integer subLineCode;

    private Integer maxWidth;
    private Integer maxLength;
    private Integer maxHeight;
    private Integer minWidth;
    private Integer minLength;
    private Integer minHeight;
    private Integer channelType;
    private Integer stockModel;

    private Integer totalQty = 0;
    private boolean isBand = false;
}
