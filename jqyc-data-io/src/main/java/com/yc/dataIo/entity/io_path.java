package com.yc.dataIo.entity;

import lombok.Data;

/**
 * @program: jqyc
 * @description: 接口数据线路信息
 * @author: lsf
 * @create: 2020-07-14 11:39
 **/
@Data
public class io_path {
    private String pathCode;
    private String pathName;
    private String pathSequence;
    private String areaCode;
    private Integer lineCode;
}
