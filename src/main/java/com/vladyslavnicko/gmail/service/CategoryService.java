package com.vladyslavnicko.gmail.service;

import com.vladyslavnicko.gmail.model.Category;

public interface CategoryService {

	Category createNewCategory(Category category);

	Category findCategoryByName(String name);

}
