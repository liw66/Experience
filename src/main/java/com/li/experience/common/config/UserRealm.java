package com.li.experience.common.config;

import com.li.experience.common.utils.ContextUtils;
import com.li.experience.modules.sys.domain.UserDO;
import com.li.experience.modules.sys.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @program: experience
 * @description:
 * @author: Liwei
 * @create: 2019-05-07 17:06
 **/
public class UserRealm extends AuthorizingRealm {

    /*
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserService userService = ContextUtils.getBean(UserService.class);
        authorizationInfo.setRoles(userService.findRoles(username));
        authorizationInfo.setStringPermissions(userService.findPermissions(username));
        return authorizationInfo;
    }

    /*
     * 用于认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        String password = String.valueOf((char[])token.getCredentials());
        UserService userService = ContextUtils.getBean(UserService.class);
        UserDO userDO = userService.getByName(username);
        if (userDO == null){
            throw new UnknownAccountException();
        }
        if (userDO.getState() == 0){
            throw new LockedAccountException("账号锁定");
        }
        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(userDO.getUsername(),
                        userDO.getPassword(),
                        ByteSource.Util.bytes(userDO.getUsername()),
                        userDO.getRealname());
        return authenticationInfo;
    }
}
