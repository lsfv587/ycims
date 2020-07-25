package com.yc.dao.entity.op;

import lombok.Data;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-16 15:51
 **/
@Data
public class op_channelBind {
    private String channelCode;
    private List<op_channelBindDet> dets;
}
