package com.yc.dataIo.entity;

import lombok.Data;

/**
 * @program: jqyc
 * @description:接口数据批次信息
 * @author: lsf
 * @create: 2020-07-14 11:28
 **/
@Data
public class io_batch {
    private String id;
    private String orderDate;
    private String lineCode;
    private String status;

}
