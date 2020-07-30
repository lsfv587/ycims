package com.yc.vo.inner;

import lombok.Data;

import java.util.List;

/**
 * @program: springboot_shior
 * @description: 系统菜单VO类
 * @author: lsf
 * @create: 2020-06-08 13:48
 **/
@Data
public class MenuVo {
    private String title;
    private String index;
    private String icon = "";
    private List<MenuVo> subs;
}
