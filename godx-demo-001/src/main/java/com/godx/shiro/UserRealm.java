package com.godx.shiro;

import com.godx.entity.User;
import com.godx.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /*
    * 执行授权逻辑
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源进行授权：
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        User user  = (User) subject.getPrincipal();
        User dbuser = userService.findById(user.getId());
        simpleAuthorizationInfo.addStringPermission(dbuser.getRole());
        return simpleAuthorizationInfo;
    }

    /*
    * 执行认证逻辑
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
       /* String name = "wangxin";
        String password = "123456";*/

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        User user = userService.findByName(token.getUsername());
        System.out.println(user.getUsername()+"/"+user.getPassword());
        //1.判断用户名
        if(user==null){
            return null;//shiro低层会抛出UnknownAccountException异常
        }
        //2.判断密码
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
