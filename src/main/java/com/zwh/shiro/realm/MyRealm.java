package com.zwh.shiro.realm;

import com.zwh.dao.UserDAO;
import com.zwh.entity.Permission;
import com.zwh.entity.Role;
import com.zwh.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Resource
    private UserDAO userDAO;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       /* String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        User user = userDAO.selectUser(primaryPrincipal);
        List<Role> roles = user.getRoles();//查角色
        //查权限
        List<Permission> permissions = new ArrayList<>();
        for (Role role : roles) {
            for (Permission permission : role.getPermissions()) {
                    if(!permissions.contains(permission)) {
                        permissions.add(permission);
                    }
            }
        }*/
        //创建授权器
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("super");
        authorizationInfo.addStringPermission("user:delete");
        authorizationInfo.addStringPermission("user:update");
        authorizationInfo.addStringPermission("user:select");
        authorizationInfo.addStringPermission("user:add");
        return authorizationInfo;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        User user = userDAO.selectUser(principal);
        AuthenticationInfo info =null;
        if (user!=null) {
            info = new SimpleAuthenticationInfo(principal, user.getPassword(), ByteSource.Util.bytes("1111"), this.getName());
        }
        return info;
    }
}
