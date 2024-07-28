package com.vladyslavnicko.gmail.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.vladyslavnicko.gmail.exception.ConflictException;
import com.vladyslavnicko.gmail.model.Category;
import com.vladyslavnicko.gmail.repository.CategoryReposytory;
import com.vladyslavnicko.gmail.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryReposytory categoryReposytory;

	@Override
	public Category createNewCategory(Category category) {
		
		if (category == null) {
			throw new ConflictException("Category is null");
		}
		
		Category oldCategory = findCategoryByName(category.getName());
		if (oldCategory != null) {
			throw new ConflictException("This category already exists!");
		}
		
		category.setCreateDate(new Date());
		
		return categoryReposytory.save(category);

	}

	@Override
	public Category findCategoryByName(String name) {
		if (StringUtils.isBlank(name)) {
			throw new ConflictException("Name is empty");
		}
	
		return categoryReposytory.findByName(name);
	}

}
