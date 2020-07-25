package com.yc.dao.entity.op;

import lombok.Data;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-17 09:34
 **/
@Data
public class op_productBind {
    private String productCode;
    private List<op_productBindDet> dets;
}
