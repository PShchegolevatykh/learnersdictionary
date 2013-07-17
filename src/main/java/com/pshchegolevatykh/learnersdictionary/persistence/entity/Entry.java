package com.pshchegolevatykh.learnersdictionary.persistence.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Entry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String meaning;

	private int popularity;

	private String transcript;

	private String word;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="CategoryId")
	private Category category;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserId")
	private User user;

	public Entry() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMeaning() {
		return this.meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public int getPopularity() {
		return this.popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public String getTranscript() {
		return this.transcript;
	}

	public void setTranscript(String transcript) {
		this.transcript = transcript;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}