package com.yc.dao.entity.st;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-23 11:19
 **/
@Data
public class st_path {
    private String id;
    private String pathCode;
    private String pathName;
    private Integer pathSequence;
    private String batchId;
    private Integer qty = 0;
}
