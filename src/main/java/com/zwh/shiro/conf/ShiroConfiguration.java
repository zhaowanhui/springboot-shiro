package com.zwh.shiro.conf;

import com.zwh.shiro.realm.MyRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {
    //shiro过滤器的配置
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //多个过滤器 AnonymousFilter 匿名过滤器 anon
        // FormAuthenticationFilter 认证过滤器 authc
        Map<String,String> map = new HashMap<>();
        map.put("/**","authc");
        map.put("/user/loginUser","anon");
        map.put("user/loginOut","anon");
        //设置登录入口的路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
    //shiro的核心对象 安全管理器
    @Bean
    public SecurityManager getSecurityManager(Realm realm,CacheManager cacheManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(cacheManager);//设置缓存
        securityManager.setRealm(realm);//设置自定义realm
        return securityManager;
    }
    //自定义realm
    @Bean
    public Realm getRealm(CredentialsMatcher credentialsMatcher){
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(credentialsMatcher);//添加自定义凭证匹配器
        return myRealm;
    }
    //选择HashedCredentialsMatcher 凭证匹配器
    @Bean
    public CredentialsMatcher getCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }
    //安全管理缓存器
    @Bean
    public CacheManager getCacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        return cacheManager;
    }
}
