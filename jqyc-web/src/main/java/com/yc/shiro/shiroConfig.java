package com.yc.shiro;

import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: jqyc
 * @description: shiro配置类
 * @author: lsf
 * @create: 2020-07-27 08:55
 **/
@Configuration
public class shiroConfig {
    @Bean
    public AuthorizingRealm myShiroRealm() {
        UserPasswordRealm customRealm = new UserPasswordRealm();
        return customRealm;
    }
    //权限管理，配置主要是Realm的管理认证
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置自定义过滤器
        Map<String, Filter> shiroFilter = shiroFilterFactoryBean.getFilters();
        shiroFilter.put("jwt",new JwtFilter());
        shiroFilterFactoryBean.setFilters(shiroFilter);
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //url拦截
        Map<String, String> map = new HashMap<>();
        //对其他url进行权限验证
        map.put("/**", "jwt");
        //登出
        map.put("/login/logout", "logout");
        //登录
        map.put("/login/login", "anon");
        //开放API文档接口
        map.put("/swagger-ui.html", "anon");
        map.put("/webjars/**","anon");
        map.put("/swagger-resources/**","anon");
        map.put("/v2/**","anon");
        //sql监控
        map.put("/druid/**","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
    //开启权限注解，如果未配置，在控制器上的权限注解将不生效
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
