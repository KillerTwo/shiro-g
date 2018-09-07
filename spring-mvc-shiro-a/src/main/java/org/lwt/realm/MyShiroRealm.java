package org.lwt.realm;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.lwt.dao.UsersMapper;
import org.lwt.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {
    
    @Autowired
    private UsersMapper userMapper;
    private int loginNum = 0;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        
        return null;
    }
    /**
     *  身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        Users user = new Users();
        user.setUsername(username);
        Users users = userMapper.selectOne(user);
        if(users == null) {
            throw new UnknownAccountException();
        }
        String password = users.getPassword();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, 
                ByteSource.Util.bytes(users.getSalt()), getName());
        return info;
    }

}
