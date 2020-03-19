package com.liumq.booksystem.realm;

import com.liumq.booksystem.dao.RoleMenuDao;
import com.liumq.booksystem.dao.UserDao;
import com.liumq.booksystem.entity.RoleMenu;
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
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserDao userDao;

    @Resource
    private RoleMenuDao roleMenuDao;

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
        // 用户 角色 菜单 对应关
        List<RoleMenu> roleMenuList = roleMenuDao.findByRoleId(user.getRole().getId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (RoleMenu roleMenu : roleMenuList) {
            info.addStringPermission(roleMenu.getMenu().getPermissions());// 给“添加用户权限”的权限
        }
        Set<String> roles = new HashSet<>();
        //设置角色名称
        roles.add(user.getRole().getName());
        info.setRoles(roles);
        return info;
    }

    /**
     * 登录、认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String name = (String) authenticationToken.getPrincipal();

        User user = userDao.findByName(name);

        if (user != null) {
            AuthenticationInfo authInfo = new SimpleAuthenticationInfo(user.getName(), user.getPwd(), "xxx");
            return authInfo;
        } else {
            return null;
        }

    }
}
