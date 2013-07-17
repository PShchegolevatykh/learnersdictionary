package com.pshchegolevatykh.learnersdictionary.persistence.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String authToken;

	private String email;

	private String password;

	private String salt;

	//bi-directional many-to-one association to Entry
	@OneToMany(mappedBy="user")
	private Set<Entry> entries;

	//bi-directional one-to-one association to Profile
	@OneToOne(mappedBy="user")
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