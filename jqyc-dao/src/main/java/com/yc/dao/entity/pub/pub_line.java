package com.yc.dao.entity.pub;

import lombok.Data;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 08:51
 **/
@Data
public class pub_line {
    private String id;
    private Integer lineCode;
    private String lineName;
    private Integer stockModel;

    private List<pub_subLine> subLines;
}
