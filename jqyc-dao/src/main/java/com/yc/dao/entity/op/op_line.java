package com.yc.dao.entity.op;

import lombok.Data;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 10:58
 **/
@Data
public class op_line {
    private Integer lineCode;
    private Integer stockModel;

    private List<op_subLine> subLines;
}
