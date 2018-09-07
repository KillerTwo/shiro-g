package org.lwt.matcher;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

    
    private Cache<String, AtomicInteger> loginRetryCache;
    private int maxRetryCount = 3;
    private String loginRetryName ;
    
    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }
    
    public RetryLimitCredentialsMatcher(CacheManager cacheManager, String loginRetryName) {
        this.loginRetryName = loginRetryName;
        this.loginRetryCache = cacheManager.getCache(loginRetryName);
    }
    
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        System.err.println("++++>>>>><<<<>>><<<>><> "+ loginRetryName);
        String username = (String) token.getPrincipal();
        // 在取出的过程如果数值已存在则值会会将值加1
        AtomicInteger retryCount = loginRetryCache.get(username);
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
            loginRetryCache.put(username, retryCount);
        }
        // 在取出的过程如果数值已存在则值会会将值加1
        if(retryCount.incrementAndGet() > maxRetryCount) {
            System.err.println("超出重试次数。");
            throw new ExcessiveAttemptsException("超出重试次数。。。");
        }
        boolean matcher = super.doCredentialsMatch(token, info);
        if(matcher) {
            loginRetryCache.remove(loginRetryName);
        }
        return matcher;
    }

    

}
