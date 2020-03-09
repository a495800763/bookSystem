package com.liumq.booksystem.realm;

import com.liumq.booksystem.dao.UserDao;
import com.liumq.booksystem.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

public class MyRealm  extends AuthorizingRealm {

    @Resource
    private UserDao userDao;

    /**
     * 授权--验证URL
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name = (String) SecurityUtils.getSubject().getPrincipal();// 得到权限
        User user = userDao.findByName(name);
        // 用户 角色 菜单 对应关系
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles= new HashSet<>();
        roles.add("管理员");
        info.addStringPermission("添加用户权限");// 给“添加用户权限”的权限
        info.setRoles(roles);
        return info;
    }

    /**
     *
     * 登录、认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String name =(String) authenticationToken.getPrincipal();

        User user = userDao.findByName(name);

        if(user!=null)
        {
            AuthenticationInfo authInfo = new SimpleAuthenticationInfo(user.getName(),user.getPwd(),"xxx");
            return authInfo;
        }
        else{
            return null ;
        }

    }
}
