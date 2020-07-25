package com.yc.dao.entity.wm;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 09:52
 **/
@Data
public class wm_stock {
    private String id;
    private String stockCode;
    private String productCode;
    private Integer qty;
}
