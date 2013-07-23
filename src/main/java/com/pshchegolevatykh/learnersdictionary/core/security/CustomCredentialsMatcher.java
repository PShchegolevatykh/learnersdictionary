package com.pshchegolevatykh.learnersdictionary.core.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

public class CustomCredentialsMatcher implements CredentialsMatcher {    

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token,
	    AuthenticationInfo info) {
	// Actual checking was already done inside JpaRealm class
	return true;
    }

}
