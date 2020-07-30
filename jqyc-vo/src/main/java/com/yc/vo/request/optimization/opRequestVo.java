package com.yc.vo.request.optimization;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-30 14:33
 **/
@Data
public class opRequestVo {
    private String orderDate;
    private Integer lineCode;
    private String[] pathIds;
}
