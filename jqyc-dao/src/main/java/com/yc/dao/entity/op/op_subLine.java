package com.yc.dao.entity.op;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 10:47
 **/
@Data
public class op_subLine {
    private Integer subLineCode;
    private Integer maxCacheNum;
    private Integer qty = 0;
}
