package com.yc.dao.entity.sys;

import lombok.Data;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-27 10:22
 **/
@Data
public class sys_menu {
    private String id;
    private String parentId;
    private String name;
    private String component;
    private String path;
    private String title;
    private String icon;
    private Integer sort;
    private Integer isUse;
    private Integer isRouter;
    private String breadTitle;
    private Integer type;
    private String permission;
}
