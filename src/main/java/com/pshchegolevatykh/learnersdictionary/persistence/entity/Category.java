package com.pshchegolevatykh.learnersdictionary.persistence.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	//bi-directional many-to-one association to Entry
	@OneToMany(mappedBy="category")
	private Set<Entry> entries;

	public Category() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Entry> getEntries() {
		return this.entries;
	}

	public void setEntries(Set<Entry> entries) {
		this.entries = entries;
	}

	public Entry addEntry(Entry entry) {
		getEntries().add(entry);
		entry.setCategory(this);

		return entry;
	}

	public Entry removeEntry(Entry entry) {
		getEntries().remove(entry);
		entry.setCategory(null);

		return entry;
	}

}