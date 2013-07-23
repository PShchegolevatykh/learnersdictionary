package com.pshchegolevatykh.learnersdictionary.core.security;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.web.env.DefaultWebEnvironment;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.env.WebEnvironment;

public class CustomEnvironmentLoaderListener extends EnvironmentLoaderListener {
    
    @Inject
    private JpaRealm jpaRealm;
    
    @Override
    protected WebEnvironment createEnvironment(ServletContext servletContext) {
        WebEnvironment environment = super.createEnvironment(servletContext);
        RealmSecurityManager rsm = (RealmSecurityManager) environment.getSecurityManager();
        jpaRealm.setCredentialsMatcher(new CustomCredentialsMatcher());
        rsm.setRealm(jpaRealm);
        ((DefaultWebEnvironment) environment).setSecurityManager(rsm);
        return environment;
    }
}
