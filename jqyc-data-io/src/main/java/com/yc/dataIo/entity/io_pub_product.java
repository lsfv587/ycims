package com.yc.dataIo.entity;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-14 14:27
 **/
@Data
public class io_pub_product {
    private String id;
    private String productCode;
    private String productName;
    private String productType;
    private Integer productWidth;
    private Integer productHeight;
    private Integer productLength;
    private String isOverseas;
    private String barCode;
    private String jbarCode;
    private Integer jqty;
    private Integer stackQty;

}
