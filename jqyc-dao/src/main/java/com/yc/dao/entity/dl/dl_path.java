package com.yc.dao.entity.dl;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-15 15:04
 **/
@Data
public class dl_path {
    private String id;
    private String pathCode;
    private String pathName;
    private String pathSequence;
    private String areaCode;
    private Integer isSort;
    private Integer lineCode;
}
