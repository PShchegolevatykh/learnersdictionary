package com.pshchegolevatykh.learnersdictionary.core.security;

import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.ejb.EJB;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.pshchegolevatykh.learnersdictionary.core.domain.User;
import com.pshchegolevatykh.learnersdictionary.persistence.dao.UserDao;

public class JpaRealm extends AuthorizingRealm {

    @EJB
    private UserDao userDao;
    @EJB
    private HashProvider hashProvider;
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection  principals) {
	if(principals == null) {
	    throw new AuthorizationException("PrincipalCollection can not be null");
	}
	
	if(principals.isEmpty()) {
	    throw new AuthorizationException("PrincipalCollection can not be empty");
	}
	// maybe should try principals.iterator().next();
	Integer userId = (Integer) principals.fromRealm(this.getName()).iterator().next();
	if (userId == null) {
	    throw new AuthorizationException("Could not get user id from session");
	}
	User user = userDao.findById(userId);
	if (user == null) {
	    throw new AuthorizationException("Could not find user with specified id");
	}
	// if role based access needed
//	SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        for (Role role : user.getRoles()) {
//            info.addRole(role.getDescription());
//            for (Permission permission : role.getPermissions()) {
//                info.addStringPermission(permition.getDescription());
//            }
//        }
	return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
	    AuthenticationToken authToken) throws AuthenticationException,
	    InvalidParameterException {
	if (authToken == null) {
	    throw new InvalidParameterException("Invalid authentication token argument");
	}
	UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authToken;
	String email = usernamePasswordToken.getUsername();
	char[] password = usernamePasswordToken.getPassword();
	if (email == null) {  
            throw new AccountException("Null emails are not allowed by this realm");  
        }
        
        if (password == null) {  
            throw new AccountException("Null passwords are not allowed by this realm");  
        }
        
        User user = userDao.findByEmail(email);
        
        if (user == null) {
            throw new UnknownAccountException("No user found with requested email");
        }
	try {
	    if (!hashProvider.validatePassword(new String(password), user.getPassword(), user.getSalt())) {
	        throw new AccountException("Invalid credentials");
	    }
	} catch (NoSuchAlgorithmException e) {
	    throw new AuthorizationException("Hash provider error", e);
	} catch (InvalidKeySpecException e) {
	    throw new AuthorizationException("Hash provider error", e);
	}
	return new SimpleAuthenticationInfo(user.getId(), user.getPassword(), this.getName());
    }

}
