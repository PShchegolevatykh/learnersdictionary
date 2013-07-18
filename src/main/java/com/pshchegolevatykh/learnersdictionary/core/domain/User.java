package com.pshchegolevatykh.learnersdictionary.core.domain;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;

    private String authToken;

    private String email;

    private String password;

    private String salt;

    private Set<Entry> entries;

    private Profile profile;

    public User() {
    }

    public int getId() {
	return this.id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getAuthToken() {
	return this.authToken;
    }

    public void setAuthToken(String authToken) {
	this.authToken = authToken;
    }

    public String getEmail() {
	return this.email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return this.password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getSalt() {
	return this.salt;
    }

    public void setSalt(String salt) {
	this.salt = salt;
    }

    public Set<Entry> getEntries() {
	return this.entries;
    }

    public void setEntries(Set<Entry> entries) {
	this.entries = entries;
    }

    public Entry addEntry(Entry entry) {
	getEntries().add(entry);
	entry.setUser(this);

	return entry;
    }

    public Entry removeEntry(Entry entry) {
	getEntries().remove(entry);
	entry.setUser(null);

	return entry;
    }

    public Profile getProfile() {
	return this.profile;
    }

    public void setProfile(Profile profile) {
	this.profile = profile;
    }

}