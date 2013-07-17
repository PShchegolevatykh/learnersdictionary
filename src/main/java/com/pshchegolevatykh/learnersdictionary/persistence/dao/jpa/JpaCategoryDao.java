package com.pshchegolevatykh.learnersdictionary.persistence.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.pshchegolevatykh.learnersdictionary.core.domain.Category;
import com.pshchegolevatykh.learnersdictionary.core.service.MapperService;
import com.pshchegolevatykh.learnersdictionary.persistence.dao.CategoryDao;

@Stateless
public class JpaCategoryDao implements CategoryDao {
	
	@PersistenceContext(unitName = "learnersDictionary")
	private EntityManager entityManager;
	
	@EJB
	private MapperService mapperService;

	@Override
	public void create(Category category) {
		final com.pshchegolevatykh.learnersdictionary.persistence.entity.Category categoryEntity = mapperService.mapTo(category, com.pshchegolevatykh.learnersdictionary.persistence.entity.Category.class);
		entityManager.persist(categoryEntity);
	}

	@Override
	public void update(Category category) {
		final com.pshchegolevatykh.learnersdictionary.persistence.entity.Category categoryEntity = mapperService.mapTo(category, com.pshchegolevatykh.learnersdictionary.persistence.entity.Category.class);
		entityManager.merge(categoryEntity);
	}

	@Override
	public void delete(Category category) {
		final com.pshchegolevatykh.learnersdictionary.persistence.entity.Category categoryEntity = mapperService.mapTo(category, com.pshchegolevatykh.learnersdictionary.persistence.entity.Category.class);
		entityManager.remove(categoryEntity);
	}

	@Override
	public Iterable<Category> findAll() {
		List<Category> categoryList = new ArrayList<Category>();
		Query query = entityManager.createQuery("select c from Category c");
		List<com.pshchegolevatykh.learnersdictionary.persistence.entity.Category> categoryEntityList = (List<com.pshchegolevatykh.learnersdictionary.persistence.entity.Category>)query.getResultList();
		
		for (com.pshchegolevatykh.learnersdictionary.persistence.entity.Category categoryEntity : categoryEntityList) {
			Category category = mapperService.mapTo(categoryEntity, Category.class);
			categoryList.add(category);
		}
		
		return categoryList;
	}
	
	
}
