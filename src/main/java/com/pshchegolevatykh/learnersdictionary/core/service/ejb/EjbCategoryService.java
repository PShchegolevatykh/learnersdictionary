package com.pshchegolevatykh.learnersdictionary.core.service.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.pshchegolevatykh.learnersdictionary.core.domain.Category;
import com.pshchegolevatykh.learnersdictionary.core.service.CategoryService;
import com.pshchegolevatykh.learnersdictionary.persistence.dao.CategoryDao;

@Stateless
public class EjbCategoryService implements CategoryService {

	@EJB
	private CategoryDao categoryDao;
	
	@Override
	public void create(Category category) {
		categoryDao.create(category);
	}

	@Override
	public void update(Category category) {
		categoryDao.update(category);
	}

	@Override
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	@Override
	public Iterable<Category> findAll() {
		return categoryDao.findAll();
	}

}
