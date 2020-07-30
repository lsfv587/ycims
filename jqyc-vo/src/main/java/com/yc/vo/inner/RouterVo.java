package com.yc.vo.inner;

import lombok.Data;

import java.util.List;

/**
 * @program: springboot_shior
 * @description: 前端路由VO类
 * @author: lsf
 * @create: 2020-06-08 13:50
 **/
@Data
public class RouterVo {
    private String component;
    private String name;
    private String path;
    private String redirect = "";
    private MetaVo meta;
    private List<RouterVo> children;
}
