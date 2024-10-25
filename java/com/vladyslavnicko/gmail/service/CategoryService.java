package com.vladyslavnicko.gmail.service;

import org.springframework.security.access.prepost.PreAuthorize;

import com.vladyslavnicko.gmail.model.Category;

public interface CategoryService {

	@PreAuthorize("hasAuthority('ADMIN')")
	Category createNewCategory(Category category);

	Category findCategoryByName(String name);

}
