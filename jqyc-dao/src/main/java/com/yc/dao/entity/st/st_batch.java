package com.yc.dao.entity.st;

import lombok.Data;

import java.util.Date;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-23 08:51
 **/
@Data
public class st_batch {
    private String id;
    private String orderDate;
    private String pickDate;
    private Integer lineCode;
    private String batchCode;
    private Integer state;
    private Integer qty = 0;
    private Integer custNum = 0;
    private Integer orderNum = 0;
    private Integer custAvgQty = 0;
    private Integer orderAvgQty = 0;
    private Date beginTm;
    private Date endTm;
    private Integer sortTm;
    private Integer faultTm;
    private Integer pauseTm;
}
