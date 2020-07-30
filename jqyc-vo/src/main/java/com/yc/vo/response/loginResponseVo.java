package com.yc.vo.response;

import com.yc.vo.inner.MenuVo;
import com.yc.vo.inner.RouterVo;
import lombok.Data;

import java.util.List;

/**
 * @program: jqyc
 * @description:
 * @author: lsf
 * @create: 2020-07-27 11:17
 **/
@Data
public class loginResponseVo {
    private String token;
    private List<RouterVo> routes;
    private List<MenuVo> menus;
}
