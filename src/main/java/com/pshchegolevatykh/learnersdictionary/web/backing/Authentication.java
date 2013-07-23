package com.pshchegolevatykh.learnersdictionary.web.backing;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.security.sasl.AuthenticationException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@ManagedBean
@RequestScoped
public class Authentication {
    public static final String HOME_URL = "hello.xhtml";
    
    private String username;
    private String password;
    private boolean remember;
    
    
    
    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }



    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }



    public boolean isRemember() {
        return remember;
    }



    public void setRemember(boolean remember) {
        this.remember = remember;
    }



    public void login() throws IOException {
	 // Example using most common scenario of username/password pair:
   
        try {
	    SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, remember));
	    SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
	    Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : HOME_URL);
	} catch (AuthenticationException e) {
	    Messages.addGlobalError("Unknown user, please try again");
	    e.printStackTrace();
	}
    }
    
    public void logout() throws IOException {
	SecurityUtils.getSubject().logout();
        Faces.invalidateSession();
        Faces.redirect(HOME_URL);
    }
}
