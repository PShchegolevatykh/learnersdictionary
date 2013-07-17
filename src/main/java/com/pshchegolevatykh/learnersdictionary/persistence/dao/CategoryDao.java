package com.pshchegolevatykh.learnersdictionary.persistence.dao;

import com.pshchegolevatykh.learnersdictionary.core.domain.Category;

public interface CategoryDao {
	void create(Category category);
	void update(Category category);
	void delete(Category category);
	Iterable<Category> findAll();
}
