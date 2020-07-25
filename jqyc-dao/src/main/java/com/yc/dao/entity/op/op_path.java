package com.yc.dao.entity.op;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-21 12:25
 **/
@Data
public class op_path {
    private String id;
    private String pathCode;
    private String batchId;
    private String pathName;
    private String pathSequence;
    private String areaCode;
    private Integer lineCode;
}
