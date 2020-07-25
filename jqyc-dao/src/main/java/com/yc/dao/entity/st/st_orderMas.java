package com.yc.dao.entity.st;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-23 11:42
 **/
@Data
public class st_orderMas {
    private String id;
    private String orderCode;
    private Integer serialNo;
    private Integer subLineCode;
    private Integer subLineQty;
    private Integer totalQty;
    private Integer exportNo = 0;
    private Integer state = 0;
    private Date sortTm;
    private String custCode;
    private String batchId;

    private List<st_orderDet> dets;
}
