package com.pshchegolevatykh.learnersdictionary.persistence.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int userId;

	private String firstName;

	private String lastName;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="UserId")
	private User user;

	public Profile() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}