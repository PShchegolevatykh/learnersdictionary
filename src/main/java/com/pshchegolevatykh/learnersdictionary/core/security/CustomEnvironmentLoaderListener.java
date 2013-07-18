package com.pshchegolevatykh.learnersdictionary.core.security;

import javax.servlet.ServletContext;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.web.env.DefaultWebEnvironment;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.env.WebEnvironment;

public class CustomEnvironmentLoaderListener extends EnvironmentLoaderListener {
    
    private JpaRealm jpaRealm = null;
    
    @Override
    protected WebEnvironment createEnvironment(ServletContext servletContext) {
        WebEnvironment environment = super.createEnvironment(servletContext);
        jpaRealm = new JpaRealm();
        RealmSecurityManager rsm = (RealmSecurityManager) environment.getSecurityManager();
        PasswordService passwordService = new DefaultPasswordService();
        PasswordMatcher passwordMatcher = new PasswordMatcher();
        passwordMatcher.setPasswordService(passwordService);
        jpaRealm.setCredentialsMatcher(passwordMatcher);
        rsm.setRealm(jpaRealm);
        ((DefaultWebEnvironment) environment).setSecurityManager(rsm);
        return environment;
    }
}
