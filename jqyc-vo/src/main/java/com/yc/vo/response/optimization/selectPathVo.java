package com.yc.vo.response.optimization;

import lombok.Data;

/**
 * @program: jqyc
 * @description: 数据优化时,选择优化的线路VO
 * @author: lsf
 * @create: 2020-07-30 10:10
 **/
@Data
public class selectPathVo {
    private String id;
    private String pathCode;
    private String pathName;
    private Integer qty;
}
