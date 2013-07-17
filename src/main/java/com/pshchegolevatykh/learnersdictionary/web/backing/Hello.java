package com.pshchegolevatykh.learnersdictionary.web.backing;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.pshchegolevatykh.learnersdictionary.core.domain.Category;
import com.pshchegolevatykh.learnersdictionary.core.service.CategoryService;

@ManagedBean
@RequestScoped
public class Hello {
	private String message = "Hello World!";
	
	@EJB
	private transient CategoryService categoryService;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Iterable<Category> getCategories() {
		return categoryService.findAll();
	}
	
}
