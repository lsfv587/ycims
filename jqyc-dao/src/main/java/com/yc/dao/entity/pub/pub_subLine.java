package com.yc.dao.entity.pub;

import lombok.Data;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 08:53
 **/
@Data
public class pub_subLine {
    private String id;
    private Integer subLineCode;
    private String subLineName;
    private Integer maxCacheNum;

    private pub_line line;
}
