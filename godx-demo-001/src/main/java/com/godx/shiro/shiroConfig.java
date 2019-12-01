package com.godx.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class shiroConfig {
    /*
    * 创建ShiroFilterFactoryBean
    *
    */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean  shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /*
        * shiro内置过滤器，
        * 常用过滤器
        * anon:无需认证（登录），可以访问
        * authc：必须认证才可以访问
        * user:如果使用rememeber me就可以直接访问
        * perms：有资源权限才可以访问
        * role:必须得到角色权限才可以访问
        * */
        Map<String,String> filter = new LinkedHashMap<>();
        filter.put("/user/add","authc");
        filter.put("/user/update","authc");
        filter.put("/user/hello","authc");
       /* //拦截退出
        filter.put("/user/logout", "logout");*/

        //授权过滤器
        //注意：当前授权拦截后，shiro会自动跳转到未授权的页面
        filter.put("/user/add","perms[admin]");
        filter.put("/user/update","perms[test]");
        //设置权限不够跳转的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/noAuth");
        //默认跳转login.jsp
        //修改跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/user/tologin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filter);
        return shiroFilterFactoryBean;
    }


    /*
    * 创建DefaultWebSecurityManager
    * */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    /*
    *
    * 创建realm
    * */
    @Bean(name ="userRealm")
    public UserRealm getrealm(){
        return new UserRealm();
    }


    /*
    *
    * 配置ShiroDialect用于thymeleaf和shiro的标签*/
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
