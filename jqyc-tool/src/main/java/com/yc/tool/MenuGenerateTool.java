package com.yc.tool;

import com.yc.dao.entity.sys.sys_menu;
import com.yc.vo.inner.MenuVo;
import com.yc.vo.inner.MetaVo;
import com.yc.vo.inner.RouterVo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: springboot_shior
 * @description: 菜单生成工具类
 * @author: lsf
 * @create: 2020-06-08 15:08
 **/
public class MenuGenerateTool {
    /**
     * @Description:生成前端菜单
     * @Author: lsf
     * @Date: 2020/6/9
     */
    public static List<MenuVo> GenerateMenuVos(List<sys_menu> menus)
    {
        List<MenuVo> menuVos = new ArrayList<>(menus.size());
        //获取所有父菜单项
        List<sys_menu> ms = menus.stream().
            filter(m -> m.getParentId() == null)
            .collect(Collectors.toList());
        //获取所有子菜单项
        List<sys_menu> sms = menus.stream().
                filter(m -> m.getParentId() != null)
                .collect(Collectors.toList());
        for (sys_menu m : ms){
            menuVos.add(getMenuVos(m,sms));
        }
        return menuVos;
    }
    /**
     * @Description:生成VUE前端路由
     * @Author: lsf
     * @Date: 2020/6/9
     */
    public static RouterVo GenerateRouterVos(List<sys_menu> menus)
    {
        //创建前端路由
        RouterVo routes = new RouterVo();
        routes.setComponent("layout");
        routes.setName("layout");
        routes.setPath("/layout");
        routes.setRedirect("/home");
        //创建子路由
        List<RouterVo> routerVos = new ArrayList<>(menus.size());
        //创建首页路由
        RouterVo hmr = new RouterVo();
        hmr.setComponent("home");
        hmr.setName("home");
        hmr.setPath("/home");
        MetaVo hmt = new MetaVo();
        hmt.setBreadTitle("首页");
        hmt.setTitle("首页");
        hmt.setFixed(true);
        hmr.setMeta(hmt);
        routerVos.add(hmr);
        //获取所有路由项
        List<sys_menu> ms = menus.stream().
                filter(m -> m.getIsRouter()== 1)
                .collect(Collectors.toList());
        for(sys_menu m : ms)
        {
            RouterVo routerVo = new RouterVo();
            MetaVo metaVo = new MetaVo();
            metaVo.setTitle(m.getTitle());
            metaVo.setBreadTitle(m.getBreadTitle());
            routerVo.setComponent(m.getComponent());
            routerVo.setName(m.getName());
            routerVo.setPath(m.getPath());
            routerVo.setMeta(metaVo);
            routerVos.add(routerVo);
        }
        routes.setChildren(routerVos);
        return routes;
    }
    /**
     * @Description:获取前端菜单接口数据
     * @return:
     * @Author: lsf
     * @Date: 2020/6/8
     */
    private static MenuVo getMenuVos(sys_menu menu,List<sys_menu> menus)
    {
        MenuVo menuVo = new MenuVo();
        //获取该菜单项的子菜单集合
        List<sys_menu> submenus = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .collect(Collectors.toList());
        //如果子菜单不为空，则递归生成子菜单项
        if (submenus != null && submenus.size() > 0){
            List<MenuVo> subs = new ArrayList<>(submenus.size());
            for(sys_menu sm : submenus)
            {
                subs.add(getMenuVos(sm,menus));
            }
            menuVo.setSubs(subs);
        }
        menuVo.setIcon(menu.getIcon());
        menuVo.setIndex(menu.getPath());
        menuVo.setTitle(menu.getTitle());
        return menuVo;
    }

}
