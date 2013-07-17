package com.pshchegolevatykh.learnersdictionary.persistence.dao;

import com.pshchegolevatykh.learnersdictionary.core.domain.Entry;

public interface EntryDao {
	void create(Entry entry);
	void update(Entry entry);
	void delete(Entry entry);
	
	Iterable<Entry> findAll();
}
