package com.yc.dataIo.entity;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-21 15:35
 **/
@Data
public class io_pub_batch {
    private Integer id;
    private String batchCode;
    private String batchName;
    private Integer batchSequence;
    private Integer type;
}
