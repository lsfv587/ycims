package com.yc.dao.entity.pub;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-21 15:12
 **/
@Data
public class pub_batch {
    private String id;
    private String batchCode;
    private String batchName;
    private Integer batchSequence;
    private Integer type;
}
