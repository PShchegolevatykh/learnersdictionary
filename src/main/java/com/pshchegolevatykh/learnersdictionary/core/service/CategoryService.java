package com.pshchegolevatykh.learnersdictionary.core.service;

import com.pshchegolevatykh.learnersdictionary.core.domain.Category;

public interface CategoryService {
	void create(Category category);
	void update(Category category);
	void delete(Category category);
	
	Iterable<Category> findAll();
}
