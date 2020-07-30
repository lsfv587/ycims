package com.yc.vo.inner;

import lombok.Data;

/**
 * @program: springboot_shior
 * @description:
 * @author: lsf
 * @create: 2020-06-08 14:08
 **/
@Data
public class MetaVo {
    private String title;
    private String breadTitle;
    private Boolean fixed = false;
}
